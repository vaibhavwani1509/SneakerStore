package com.otta.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otta.common.domain.model.Sneaker
import com.otta.search.domain.GetAllAvailableSneaker
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getAllAvailableSneaker: GetAllAvailableSneaker
): ViewModel() {

    /**Observables*/
    private var mutableSneakersList = MutableSharedFlow<List<Sneaker>>()
    val sneakersList = mutableSneakersList.asSharedFlow()

    fun getSneakerList() {
        viewModelScope.launch {
            val sneakersList = async { getAllAvailableSneaker.invoke() }
            mutableSneakersList.emit(sneakersList.await())
        }
    }



}