package com.ztx.skeleton.presenter.components.repository

import android.os.Build
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class RepositoryLoadingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RepositoryLoadingItem()
        }
    }

    @Test
    fun testAvatarShimmerBoxIsDisplayed() {
        composeTestRule.onNodeWithTag("avatarShimmerBox")
            .assertIsDisplayed()
            .assertHeightIsEqualTo(40.dp)
            .assertWidthIsEqualTo(40.dp)
    }

    @Test
    fun testTitleShimmerBoxIsDisplayed() {
        composeTestRule.onNodeWithTag("titleShimmerBox")
            .assertIsDisplayed()
            .assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun testDescriptionShimmerBoxIsDisplayed() {
        composeTestRule.onNodeWithTag("descriptionShimmerBox")
            .assertIsDisplayed()
            .assertHeightIsEqualTo(32.dp)
    }
}
