package com.example.jks_compose_kt.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 底部导航栏项目数据类
 */
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)
