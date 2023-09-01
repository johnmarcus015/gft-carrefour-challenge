package com.ztx.skeleton.presenter.screen.users

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ztx.skeleton.presenter.components.Header
import com.ztx.skeleton.presenter.components.SearchField
import com.ztx.skeleton.presenter.components.error.ErrorFactory
import com.ztx.skeleton.presenter.components.error.ErrorUserNotFound
import com.ztx.skeleton.presenter.components.user.UserItem
import com.ztx.skeleton.presenter.components.user.UserLoadingItem
import com.ztx.skeleton.presenter.uistate.UsersUiState
import com.ztx.skeleton.presenter.uistate.UsersUiState.ConnectionError
import com.ztx.skeleton.presenter.uistate.UsersUiState.GenericError
import com.ztx.skeleton.presenter.uistate.UsersUiState.Loading
import com.ztx.skeleton.presenter.uistate.UsersUiState.UserNotfound
import com.ztx.skeleton.presenter.uistate.UsersUiState.UsersList
import sampleUsers
import java.net.ConnectException

@Composable
fun UsersScreen(
    navController: NavHostController,
    uiState: UsersUiState,
    swipeRefreshState: SwipeRefreshState,
    onPullRefresh: () -> Unit? = {},
    onPagination: (lastUserId: Int) -> Unit? = {},
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            onPullRefresh.invoke()
            Toast.makeText(context, "Usuários atualizados!", Toast.LENGTH_LONG)
                .show()
        },
        modifier = Modifier.testTag("swipeRefreshUsersScreen")
    ) {
        Surface {

            Column(modifier = modifier.fillMaxSize()) {

                Header(subTitle = "Users")

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
                                    UserLoadingItem()
                                }
                            }
                        }
                    }

                    is UsersList -> {
                        val users = uiState.users
                        Column(
                            modifier = modifier
                        ) {
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
                                    UserItem(
                                        user = currentUser,
                                        onClick = {
                                            navController.navigate(
                                                "repositories/${currentUser.login}"
                                            )
                                        })
                                    if (index >= users.size - 1) {
                                        onPagination(users.last().id)
                                    }
                                }
                            }
                        }
                    }

                    is UserNotfound -> {
                        ErrorUserNotFound()
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
}

@Preview(showBackground = true)
@Composable
fun UsersLoadingPreview() {
    UsersScreen(
        navController = rememberNavController(),
        uiState = Loading(),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun UsersListPreview() {
    UsersScreen(
        navController = rememberNavController(),
        uiState = UsersList(
            users = sampleUsers
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun UserNotFoundPreview() {
    UsersScreen(
        navController = rememberNavController(),
        uiState = UserNotfound(),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun ConnectionErrorPreview() {
    UsersScreen(
        navController = rememberNavController(),
        uiState = ConnectionError(
            error = ConnectException("Connection error")
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}

@Preview(showBackground = true)
@Composable
fun GenericErrorPreview() {
    UsersScreen(
        navController = rememberNavController(),
        uiState = GenericError(
            error = Exception("Generic error")
        ),
        swipeRefreshState = SwipeRefreshState(isRefreshing = false)
    )
}