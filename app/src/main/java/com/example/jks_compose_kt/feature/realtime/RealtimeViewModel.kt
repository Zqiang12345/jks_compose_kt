package com.example.jks_compose_kt.feature.realtime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 实时 ViewModel
 * MVVM 架构中的 ViewModel 层
 */
class RealtimeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RealtimeUiState())
    val uiState: StateFlow<RealtimeUiState> = _uiState.asStateFlow()

    fun connect() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isConnected = true,
                isLoading = false
            )
        }
    }

    fun disconnect() {
        _uiState.value = _uiState.value.copy(isConnected = false)
    }
}

data class RealtimeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isConnected: Boolean = false,
    val lastMessage: String? = null
)

