package com.ztx.skeleton.mocks


import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ztx.skeleton.presenter.navigation.Routes

@Composable
fun MockNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.splashscreenRoute
    ) {
        composable(Routes.splashscreenRoute) {
            Box(modifier = Modifier.testTag("mockSplashscreen")) { /* Mock SplashScreen */ }
        }
        composable(Routes.usersRoute) {
            Box(modifier = Modifier.testTag("mockUsersScreen")) { /* Mock UsersScreen */ }
        }
        composable(Routes.repositoriesRoute) {
            Box(modifier = Modifier.testTag("mockRepositoriesScreen")) { /* Mock RepositoriesScreen */ }
        }
    }
}
