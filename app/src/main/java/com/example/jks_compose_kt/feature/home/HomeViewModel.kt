package com.example.jks_compose_kt.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 首页 ViewModel
 * MVVM 架构中的 ViewModel 层
 * 负责管理 UI 相关的数据和业务逻辑
 */
class HomeViewModel : ViewModel() {
    
    // UI 状态
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // 示例：加载数据
    fun loadData() {
        viewModelScope.launch {
            // 这里可以调用 Repository 或 UseCase 来获取数据
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                // data = ...
            )
        }
    }
}

/**
 * 首页 UI 状态
 * 使用 data class 定义 UI 状态，便于状态管理
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)
