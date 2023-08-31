package com.ztx.skeleton.presenter.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ztx.skeleton.presenter.components.Header
import com.ztx.skeleton.presenter.components.SearchField
import com.ztx.skeleton.presenter.components.error.Error
import com.ztx.skeleton.presenter.components.error.ErrorUserNotFound
import com.ztx.skeleton.presenter.components.user.UserItem
import com.ztx.skeleton.presenter.components.user.UserLoadingItem
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.uistate.UiStateHome
import kotlinx.coroutines.flow.filterNotNull
import java.net.ConnectException
import kotlin.random.Random


@Composable
fun HomeScreen(
    uiState: UiStateHome,
    swipeRefreshState: SwipeRefreshState? = null,
    onPullRefresh: () -> Unit? = {},
    onPagination: (lastUserId: Int) -> Unit? = {},
    modifier: Modifier = Modifier
) {
    Surface {
        Column(modifier = modifier.fillMaxSize()) {
            Header()
            when (uiState) {
                is UiStateHome.StateHomeLoading -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        )
                    ) {
                        for (i in 0..15) {
                            item {
                                UserLoadingItem()
                            }
                        }
                    }
                }

                is UiStateHome.StateHomeListUsers -> {
                    val users = uiState.users
                    Column(
                        modifier = modifier
                    ) {
                        val context = LocalContext.current
                        SearchField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 8.dp,
                                    top = 16.dp
                                ),
                            onSearchAction = { query ->
                                Toast.makeText(context, query, Toast.LENGTH_LONG).show()
                            })
                        swipeRefreshState?.let {
                            SwipeRefresh(
                                state = swipeRefreshState,
                                onRefresh = {
                                    onPullRefresh.invoke()
                                    Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_LONG)
                                        .show()
                                }) {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    contentPadding = PaddingValues(
                                        top = 8.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    )
                                ) {
                                    items(users.size) { index ->
                                        val currentUser = users[index]
                                        UserItem(user = currentUser)
                                        if (index >= users.size - 1) {
                                            onPagination(users.last().id)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                is UiStateHome.StateHomeUserNotfound -> {
                    ErrorUserNotFound()
                }

                is UiStateHome.StateHomeError -> {
                    val error = uiState.error
                    error.message?.let {
                        Error(message = it)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeLoadingPreview() {
    HomeScreen(uiState = UiStateHome.StateHomeLoading())
}

@Preview(showSystemUi = true)
@Composable
fun HomeSuccessPreview() {
    val users = mutableListOf<UserUiData>()
    for (i in 0..15) {
        users.add(
            UserUiData(
                id = 1,
                login = "user${Random.nextInt(10000, Int.MAX_VALUE)}",
                avatarUrl = "https://www.github.com/placeholder"
            )
        )
    }
    HomeScreen(
        uiState = UiStateHome.StateHomeListUsers(
            users = users
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview
@Composable
fun HomeUserNotFoundPreview() {
    HomeScreen(uiState = UiStateHome.StateHomeUserNotfound())
}

@Preview(showSystemUi = true)
@Composable
fun HomeErrorPreview() {
    HomeScreen(
        uiState = UiStateHome.StateHomeError(
            error = ConnectException("Connection error")
        )
    )
}