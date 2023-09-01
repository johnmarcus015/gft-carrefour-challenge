package com.ztx.skeleton.presenter.components.repository

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.presenter.model.RepositoryUiData
import com.ztx.skeleton.presenter.model.UserUiData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class RepositoryItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Verify if repositoryItem shows name correctly`() {
        composeTestRule.setContent {
            RepositoryItem(
                repository = RepositoryUiData(
                    name = "Retrofit",
                    description = "Library to make http requests easily in android",
                    owner = UserUiData(
                        id = 1,
                        login = "mojombo",
                        avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
                    )
                )
            )
        }
        composeTestRule.onNodeWithText("Retrofit").assertIsDisplayed()
    }

    @Test
    fun `Verify if repositoryItem shows description correctly`() {
        composeTestRule.setContent {
            RepositoryItem(
                repository = RepositoryUiData(
                    name = "Retrofit",
                    description = "Library to make http requests easily in android",
                    owner = UserUiData(
                        id = 1,
                        login = "mojombo",
                        avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
                    )
                )
            )
        }
        composeTestRule.onNodeWithText("Library to make http requests easily in android")
            .assertIsDisplayed()
    }

    @Test
    fun `Verify if repositoryItem shows default text when description is null`() {
        composeTestRule.setContent {
            RepositoryItem(
                repository = RepositoryUiData(
                    name = "Retrofit",
                    description = null,
                    owner = UserUiData(
                        id = 1,
                        login = "mojombo",
                        avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
                    )
                )
            )
        }
        composeTestRule.onNodeWithText("Repository without description").assertIsDisplayed()
    }

    @Test
    fun `Verify if repositoryItem shows avatar image correctly`() {
        composeTestRule.setContent {
            RepositoryItem(
                repository = RepositoryUiData(
                    name = "Retrofit",
                    description = "Library to make http requests easily in android",
                    owner = UserUiData(
                        id = 1,
                        login = "mojombo",
                        avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
                    )
                )
            )
        }
        val avatarNode = composeTestRule.onNode(
            hasContentDescription("owner's image of repository")
        )
        avatarNode.assertIsDisplayed()
    }
}
