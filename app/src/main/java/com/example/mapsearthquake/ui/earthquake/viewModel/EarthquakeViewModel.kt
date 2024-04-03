package com.example.mapsearthquake.ui.earthquake.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapsearthquake.data.model.EarthquakeListResponse
import com.example.mapsearthquake.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(emptyList<EarthquakeListResponse.Result>())
    val state : StateFlow<List<EarthquakeListResponse.Result>>
        get() = _state

    init {
        viewModelScope.launch {
            val result = repository.getEarthquake()
            _state.value = result
        }

    }

}
