package com.ztx.skeleton.presenter

import android.content.Context
import android.os.Build
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ztx.skeleton.mocks.MockNavGraph
import com.ztx.skeleton.presenter.navigation.Routes
import com.ztx.skeleton.utils.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@LargeTest
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [Build.VERSION_CODES.P],
    manifest = Config.NONE,
    application = HiltTestApplication::class
)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            context = LocalContext.current
            navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MockNavGraph(navController = navController)
        }
    }

    @Test
    fun splashscreenComposableIsDisplayed() {
        composeTestRule.onNodeWithTag("mockSplashscreen").assertExists()
    }

    @Test
    fun usersScreenComposableIsDisplayed() {
        // Navigate to UsersScreen route
        composeTestRule.runOnUiThread {
            navController.navigate(Routes.usersRoute)
        }
        composeTestRule.onNodeWithTag("mockUsersScreen").assertExists()
    }

    @Test
    fun repositoriesScreenComposableIsDisplayed() {
        // Navigate to RepositoriesScreen route
        composeTestRule.runOnUiThread {
            navController.navigate("repositories/username")
        }
        composeTestRule.onNodeWithTag("mockRepositoriesScreen").assertExists()
    }
}
