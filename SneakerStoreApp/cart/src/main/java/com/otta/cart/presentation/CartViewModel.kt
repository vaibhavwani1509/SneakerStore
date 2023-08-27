package com.otta.cart.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otta.cart.domain.GetAllCartItems
import com.otta.cart.domain.GetOrderDetails
import com.otta.cart.domain.RemoveItemFromCart
import com.otta.common.domain.model.OrderDetails
import com.otta.common.domain.model.Sneaker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartItems: GetAllCartItems,
    private val removeItemFromCart: RemoveItemFromCart,
    private val getOrderDetails: GetOrderDetails
) : ViewModel() {

    /**Observables*/
    private var mutableCartItemsList = MutableSharedFlow<List<Sneaker>>()
    val cartItemsList = mutableCartItemsList.asSharedFlow()

    private var mutableOrderDetails = MutableSharedFlow<OrderDetails>()
    val orderDetails = mutableOrderDetails.asSharedFlow()

    fun getCartItemsList() {
        viewModelScope.launch {
            val sneakersList = async { getAllCartItems.invoke() }
            mutableCartItemsList.emit(sneakersList.await())
            getFinalOrderDetails()
        }
    }

    fun removeCartItem(sneakerId: String) {
        viewModelScope.launch {
            val removeJob = async { removeItemFromCart.invoke(sneakerId) }
            removeJob.await()
            getCartItemsList()
        }
    }

    fun getFinalOrderDetails() {
        viewModelScope.launch {
            val orderDetailsDeferred = async { getOrderDetails.invoke() }
            mutableOrderDetails.emit(orderDetailsDeferred.await())
            Log.e("bala", "Final details 2 - $orderDetails")
        }
    }
}