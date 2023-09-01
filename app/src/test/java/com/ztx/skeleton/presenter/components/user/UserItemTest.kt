package com.ztx.skeleton.presenter.components.user

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.presenter.model.UserUiData
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class UserItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Verify UserItem renders user login correctly`() = runTest {
        val testUser = UserUiData(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
        )
        composeTestRule.setContent {
            UserItem(
                user = testUser,
                onClick = {}
            )
        }
        composeTestRule.onNodeWithText("1. mojombo").assertExists()
    }

    @Test
    fun `Verify UserItem onClick works correctly`() = runTest {
        var clickCounter = 0
        val testUser = UserUiData(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
        )
        composeTestRule.setContent {
            UserItem(
                user = testUser,
                onClick = {
                    clickCounter++
                }
            )
        }
        composeTestRule.onNodeWithTag("cardUserItem").performClick()
        assert(clickCounter == 1) { "Expected clickCounter to be 1 but was $clickCounter" }
    }
}
