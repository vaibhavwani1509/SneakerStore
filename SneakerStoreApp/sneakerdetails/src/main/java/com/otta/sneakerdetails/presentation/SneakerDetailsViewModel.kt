package com.otta.sneakerdetails.presentation

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otta.common.domain.model.Sneaker
import com.otta.sneakerdetails.domain.AddSneakerToCart
import com.otta.sneakerdetails.domain.CheckIsAlreadyInTheCart
import com.otta.sneakerdetails.domain.GetSneakerById
import com.otta.sneakerdetails.domain.RemoveSneakerFromCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailsViewModel @Inject constructor(
    private val getSneakerById: GetSneakerById,
    private val addSneakerToCart: AddSneakerToCart,
    private val checkIsAlreadyInTheCart: CheckIsAlreadyInTheCart,
    private val removeSneakerFromCart: RemoveSneakerFromCart
) : ViewModel() {

    var sneaker: Sneaker? = null
    var isInCart: Boolean = false

    /**Observables*/
    private var mutableIsSneakerInCart = MutableSharedFlow<Boolean>()
    val isSneakerInCart = mutableIsSneakerInCart.asSharedFlow()

    fun getSneaker(sneakerId: String) {
        viewModelScope.launch {
            val sneakerDetails = async { getSneakerById.invoke(sneakerId) }
            sneaker = sneakerDetails.await()
            isSneakerPresentInCart()
        }
    }

    private fun isSneakerPresentInCart() {
        sneaker?.let { sneak ->
            viewModelScope.launch {
                val sneakerDetails =
                    async { checkIsAlreadyInTheCart.invoke(sneakerId = sneak.id) }
                isInCart = sneakerDetails.await()
                mutableIsSneakerInCart.emit(isInCart)
            }
        }
    }

    fun performAdToCartAction() {
        if (isInCart)
            removeFromCart()
        else
            addToCart()
    }

    private fun addToCart() {
        sneaker?.let {
            addSneakerToCart.invoke(it.id)
            isSneakerPresentInCart()
        }
    }

    private fun removeFromCart() {
        sneaker?.let {
            removeSneakerFromCart.invoke(it.id)
            isSneakerPresentInCart()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun hapticFeedback(context: Context) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator
        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}