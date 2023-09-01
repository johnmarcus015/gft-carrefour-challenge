package com.ztx.skeleton.presenter.screen.repositories

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ztx.skeleton.presenter.components.Header
import com.ztx.skeleton.presenter.components.error.ErrorFactory
import com.ztx.skeleton.presenter.components.repository.RepositoryItem
import com.ztx.skeleton.presenter.components.repository.RepositoryLoadingItem
import com.ztx.skeleton.presenter.model.RepositoryUiData
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.ConnectionError
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.GenericError
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.Loading
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.RepositoriesList
import sampleRepositories
import java.net.ConnectException
import kotlin.random.Random

@Composable
fun RepositoriesScreen(
    uiState: RepositoriesUiState,
    swipeRefreshState: SwipeRefreshState,
    onPullRefresh: () -> Unit? = {},
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            onPullRefresh.invoke()
            Toast.makeText(context, "Repositórios atualizados!", Toast.LENGTH_LONG)
                .show()
        },
        modifier = Modifier.testTag("swipeRefreshRepositoriesScreen")
    ) {

        Column(modifier = modifier.fillMaxSize()) {

            Header(subTitle = "Repositories")

            when (uiState) {
                is Loading -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        )
                    ) {
                        for (i in 0..15) {
                            item {
                                RepositoryLoadingItem()
                            }
                        }
                    }
                }

                is RepositoriesList -> {
                    val repositories = uiState.repositories
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                    ) {
                        items(repositories.size) { i ->
                            val currentRepository = repositories[i]
                            RepositoryItem(repository = currentRepository)
                        }
                    }
                }

                is ConnectionError -> {
                    uiState.error.message?.let {
                        ErrorFactory.CreateConnectionError(message = it)
                    }
                }

                is GenericError -> {
                    uiState.error.message?.let {
                        ErrorFactory.CreateGenericError(message = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoriesLoadingPreview() {
    RepositoriesScreen(
        uiState = Loading(),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun RepositoriesListPreview() {
    RepositoriesScreen(
        uiState = RepositoriesList(
            repositories = sampleRepositories
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun ConnectionErrorPreview() {
    RepositoriesScreen(
        uiState = ConnectionError(
            error = ConnectException("Connection error")
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun GenericErrorPreview() {
    RepositoriesScreen(
        uiState = GenericError(
            error = Exception("Generic error")
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}