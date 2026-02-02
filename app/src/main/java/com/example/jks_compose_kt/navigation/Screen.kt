package com.example.jks_compose_kt.navigation

/**
 * 应用路由定义
 * 使用 sealed class 定义所有可导航的屏幕
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Plan : Screen("plan")
    object Realtime : Screen("realtime")
    object Profile : Screen("profile")
}
