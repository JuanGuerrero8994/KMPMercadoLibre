package org.example.kmpmercadolibre.ui.screen.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.domain.model.SearchResult
import org.example.kmpmercadolibre.domain.usecases.SearchUseCase

class SearchViewModel(private val useCase: SearchUseCase) : ViewModel() {

    private val _searchState = MutableStateFlow<Resource<SearchResult>>(Resource.Loading)
    val searchState: StateFlow<Resource<SearchResult>> get() = _searchState

    fun search(query: String) {
        viewModelScope.launch {
            useCase.search(query).collect { resource ->
                _searchState.value = resource
            }
        }
    }
}