package com.otta.sneakerslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otta.common.domain.model.Sneaker
import com.otta.sneakerslist.domain.GetAllSneakers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakersListViewModel @Inject constructor(
    private val getAllSneakers: GetAllSneakers
): ViewModel() {

    /**Observables*/
    private var mutableSneakersList = MutableSharedFlow<List<Sneaker>>()
    val sneakersList = mutableSneakersList.asSharedFlow()

    fun getSneakerList() {
        viewModelScope.launch {
            val sneakersList = async { getAllSneakers.invoke() }
            mutableSneakersList.emit(sneakersList.await())
        }
    }
}