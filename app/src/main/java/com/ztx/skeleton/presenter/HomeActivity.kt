package com.ztx.skeleton.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ztx.skeleton.presenter.screen.HomeScreen
import com.ztx.skeleton.presenter.ui.theme.SkeletonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkeletonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val uiState by viewModel.uiState.collectAsState()
                    val isLoading by viewModel.isLoading.collectAsState()
                    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

                    HomeScreen(
                        uiState = uiState,
                        swipeRefreshState = swipeRefreshState,
                        onPullRefresh = { viewModel.getUsers() },
                        onPagination = { lastUserId -> viewModel.getUsersPaginating(lastUserId) }
                    )

                    LaunchedEffect(Unit) {
                        viewModel.getUsers()
                    }
                }
            }
        }
    }
}
