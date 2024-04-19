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
class EarthquakeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<EarthquakeListResponse?>(null)
    val state: StateFlow<EarthquakeListResponse?>
        get() = _state

    init {
        viewModelScope.launch {
            try {
                val result = mainRepository.getEarthquake()
                _state.value = result
            } catch (e: Exception) {
                _state.value = null
            }
        }
    }
}

