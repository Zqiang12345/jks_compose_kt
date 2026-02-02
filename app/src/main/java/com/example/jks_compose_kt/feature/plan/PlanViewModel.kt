package com.example.jks_compose_kt.feature.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 计划 ViewModel
 * MVVM 架构中的 ViewModel 层
 */
class PlanViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlanUiState())
    val uiState: StateFlow<PlanUiState> = _uiState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                planCount = _uiState.value.planCount
            )
        }
    }
}

data class PlanUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val planCount: Int = 0
)

