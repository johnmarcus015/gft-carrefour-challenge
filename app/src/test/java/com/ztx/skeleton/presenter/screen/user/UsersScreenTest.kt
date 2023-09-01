package com.ztx.skeleton.presenter.screen.user

import android.os.Build
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ztx.skeleton.presenter.screen.users.UsersScreen
import com.ztx.skeleton.presenter.screen.users.UsersViewModel
import com.ztx.skeleton.presenter.ui.theme.SkeletonTheme
import com.ztx.skeleton.presenter.uistate.UsersUiState.Loading
import com.ztx.skeleton.utils.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class UsersScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val viewModel = mockk<UsersViewModel>()

    @Before
    fun setUp() {
        every { viewModel.uiState } returns MutableStateFlow(Loading())
        every { viewModel.isLoading } returns MutableStateFlow(false)
    }

    @Test
    fun `Verify if UserScreen is showed correctly`() {
        // Arrange
        composeTestRule.setContent {
            SkeletonTheme {
                Surface {
                    UsersScreen(
                        navController = rememberNavController(),
                        uiState = Loading(),
                        modifier = Modifier.testTag("userScreen"),
                        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
                    )
                }
            }
        }
        composeTestRule.waitForIdle()
        // Assert
        composeTestRule.onNode(hasTestTag("userScreen"))
            .assertExists()
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("swipeRefreshUsersScreen").assertExists()
    }
}
