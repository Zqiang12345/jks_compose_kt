package com.example.jks_compose_kt.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jks_compose_kt.feature.home.HomeScreen
import com.example.jks_compose_kt.feature.plan.PlanScreen
import com.example.jks_compose_kt.feature.profile.ProfileScreen
import com.example.jks_compose_kt.feature.realtime.RealtimeScreen

/**
 * 底部导航栏项目列表
 */
val bottomNavItems = listOf(
    BottomNavItem(
        label = "首页",
        icon = Icons.Default.Home,
        route = Screen.Home.route
    ),
    BottomNavItem(
        label = "计划",
        icon = Icons.Default.DateRange,
        route = Screen.Plan.route
    ),
    BottomNavItem(
        label = "实时",
        icon = Icons.Default.Phone,
        route = Screen.Realtime.route
    ),
    BottomNavItem(
        label = "我的",
        icon = Icons.Default.Person,
        route = Screen.Profile.route
    )
)

/**
 * 主导航组件
 * 包含底部导航栏和导航图
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // 弹出到导航图的起始目的地，避免在返回栈中堆积
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // 避免同一目的地的多个副本
                                launchSingleTop = true
                                // 恢复状态
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Plan.route) {
                PlanScreen()
            }
            composable(Screen.Realtime.route) {
                RealtimeScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
