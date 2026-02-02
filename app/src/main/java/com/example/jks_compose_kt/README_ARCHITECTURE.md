# 项目架构说明

## 架构模式：MVVM (Model-View-ViewModel)

本项目采用 **MVVM（Model-View-ViewModel）** 架构模式，这是 Android 官方推荐的架构模式。

### 架构层次说明

```
┌─────────────────────────────────────┐
│           View (UI Layer)           │
│  - Compose Screens                  │
│  - 观察 ViewModel 的状态              │
└─────────────────┬───────────────────┘
                  │
                  │ 观察 StateFlow/State
                  │
┌─────────────────▼───────────────────┐
│        ViewModel (Logic Layer)      │
│  - 管理 UI 状态                      │
│  - 处理业务逻辑                      │
│  - 调用 Repository/UseCase          │
└─────────────────┬───────────────────┘
                  │
                  │ 调用
                  │
┌─────────────────▼───────────────────┐
│      Model (Data Layer)              │
│  - Repository                        │
│  - DataSource (Local/Remote)         │
│  - Data Models                       │
└─────────────────────────────────────┘
```

### 项目结构

```
app/src/main/java/com/example/jks_compose_kt/
├── MainActivity.kt                    # 应用入口
├── navigation/                        # 导航相关
│   ├── Screen.kt                     # 路由定义
│   ├── BottomNavItem.kt              # 底部导航项
│   └── MainNavigation.kt             # 主导航组件
├── feature/                          # 功能模块（按功能组织）
│   ├── home/                         # 首页功能模块
│   │   ├── HomeScreen.kt             # 首页 UI（View 层）
│   │   └── HomeViewModel.kt          # 首页 ViewModel
│   ├── plan/                         # 计划功能模块
│   │   └── PlanScreen.kt
│   ├── realtime/                     # 实时功能模块
│   │   └── RealtimeScreen.kt
│   └── profile/                      # 个人中心功能模块
│       └── ProfileScreen.kt
└── ui/
    └── theme/                        # 主题相关
```

### 为什么使用 feature 包？

使用 `feature` 包而不是 `screens` 包有以下优势：

1. **功能模块化**：每个 feature 代表一个独立的功能模块，包含该功能的所有相关代码（UI、ViewModel、Repository、UseCase 等）
2. **清晰的组织结构**：便于按功能组织代码，而不是按技术层次
3. **便于扩展**：当功能复杂时，可以在 feature 下添加更多子包：
   ```
   feature/
   ├── home/
   │   ├── ui/              # UI 组件
   │   ├── domain/          # 业务逻辑（UseCase）
   │   ├── data/            # 数据层（Repository, DataSource）
   │   └── di/              # 依赖注入模块
   ```
4. **便于模块化**：如果将来需要将应用拆分成多个 Gradle 模块，feature 结构更容易迁移
5. **符合现代 Android 架构**：Google 推荐的架构指南中也提到了按功能组织代码

### MVVM 优势

1. **关注点分离**：UI、业务逻辑和数据层清晰分离
2. **可测试性**：ViewModel 可以独立测试，不依赖 UI
3. **生命周期感知**：ViewModel 自动处理配置变更
4. **响应式编程**：使用 StateFlow/Flow 实现响应式数据流

### 使用方式

#### 在 Screen 中使用 ViewModel

```kotlin
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    
    // 使用 uiState 渲染 UI
    when {
        uiState.isLoading -> LoadingIndicator()
        uiState.error != null -> ErrorMessage(uiState.error)
        else -> Content()
    }
}
```

### 扩展建议

1. **添加 Repository 层**：用于数据管理
   ```
   data/
   ├── repository/
   │   └── HomeRepository.kt
   └── local/
       └── database/
   ```

2. **添加 UseCase 层**：用于业务逻辑封装
   ```
   domain/
   └── usecase/
       └── GetHomeDataUseCase.kt
   ```

3. **添加依赖注入**：使用 Hilt 或 Koin
   ```
   di/
   └── AppModule.kt
   ```

### 技术栈

- **UI**: Jetpack Compose
- **导航**: Navigation Compose
- **架构**: MVVM
- **状态管理**: StateFlow / Compose State
- **异步**: Kotlin Coroutines
