package com.ztx.skeleton.presenter.screen.repository

import android.os.Build
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ztx.skeleton.presenter.screen.repositories.RepositoriesScreen
import com.ztx.skeleton.presenter.screen.repositories.RepositoriesViewModel
import com.ztx.skeleton.presenter.ui.theme.SkeletonTheme
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.Loading
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
class RepositoriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val viewModel = mockk<RepositoriesViewModel>()

    @Before
    fun setUp() {
        every { viewModel.uiState } returns MutableStateFlow(Loading())
        every { viewModel.isLoading } returns MutableStateFlow(false)
    }

    @Test
    fun `Verify if RepositoriesScreen is showed correctly`() {
        // Arrange
        composeTestRule.setContent {
            SkeletonTheme {
                Surface {
                    RepositoriesScreen(
                        uiState = Loading(),
                        modifier = Modifier.testTag("repositoriesScreen"),
                        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
                    )
                }
            }
        }
        composeTestRule.waitForIdle()
        // Assert
        composeTestRule.onNode(hasTestTag("repositoriesScreen"))
            .assertExists()
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("swipeRefreshRepositoriesScreen").assertExists()
    }
}
