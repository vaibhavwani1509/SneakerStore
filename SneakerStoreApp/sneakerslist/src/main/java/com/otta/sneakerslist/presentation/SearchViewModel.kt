package com.otta.sneakerslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otta.common.domain.model.Sneaker
import com.otta.sneakerslist.domain.GetAllSneakers
import com.otta.sneakerslist.domain.SearchSneaker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSneaker: SearchSneaker
): ViewModel() {

    /**Observables*/
    private var mutableSneakersList = MutableSharedFlow<List<Sneaker>>()
    val sneakersList = mutableSneakersList.asSharedFlow()

    fun search(keyword: String) {
        viewModelScope.launch {
            val sneakersList = async { searchSneaker.invoke(keyword = keyword) }
            mutableSneakersList.emit(sneakersList.await())
        }
    }

    fun getValidInputValue(input : String?): String {
        return if (input == null || input.isNullOrBlank() || input.isNullOrEmpty())
            ""
        else
            input.toString()
    }
}