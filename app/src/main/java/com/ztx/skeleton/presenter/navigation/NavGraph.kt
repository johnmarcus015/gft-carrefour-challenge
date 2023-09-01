package com.ztx.skeleton.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ztx.skeleton.presenter.screen.SplashScreen
import com.ztx.skeleton.presenter.screen.repositories.RepositoriesScreen
import com.ztx.skeleton.presenter.screen.repositories.RepositoriesViewModel
import com.ztx.skeleton.presenter.screen.users.UsersScreen
import com.ztx.skeleton.presenter.screen.users.UsersViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.splashscreenRoute
    ) {

        composable(Routes.splashscreenRoute) {
            SplashScreen(
                navController = navController,
                modifier = Modifier.testTag("splashScreen")
            )
        }

        composable(Routes.usersRoute) {
            val viewModel = hiltViewModel<UsersViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

            UsersScreen(
                navController = navController,
                uiState = uiState,
                swipeRefreshState = swipeRefreshState,
                onPullRefresh = { viewModel.getUsers() },
                onPagination = { lastUserId -> viewModel.getUsersPaginating(lastUserId) },
                onSearchAction = { username -> viewModel.getUser(username) },
                onReloadScreen = { viewModel.getUsers() },
                modifier = Modifier.testTag("userScreen")
            )

            LaunchedEffect(Unit) {
                viewModel.getUsers()
            }
        }

        composable(
            Routes.repositoriesRoute,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel = hiltViewModel<RepositoriesViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

            RepositoriesScreen(
                uiState = uiState,
                swipeRefreshState = swipeRefreshState,
                onReloadScreen = {
                    backStackEntry.arguments?.getString("username")?.let {
                        viewModel.getRepositories(it)
                    }
                },
                modifier = Modifier.testTag("repositoriesScreen")
            )

            LaunchedEffect(Unit) {
                backStackEntry.arguments?.getString("username")?.let {
                    viewModel.getRepositories(it)
                }
            }
        }
    }
}

object Routes {
    const val usersRoute = "users"
    const val splashscreenRoute = "splashscreen"
    const val repositoriesRoute = "repositories/{username}"
}