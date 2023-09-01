package com.ztx.skeleton.presenter.components.user

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class UserLoadingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            UserLoadingItem()
        }
    }

    @Test
    fun `Verify UserLoadingItem displays avatar shimmer image correctly`() = runTest {
        composeTestRule.onNodeWithTag("shimmerAvatarUserLoadingItem")
            .assertHeightIsEqualTo(60.dp)
            .assertWidthIsEqualTo(60.dp)
    }

    @Test
    fun `Verify UserLoadingItem displays avatar shimmer login correctly`() = runTest {
        composeTestRule.onNodeWithTag("shimmerLoginUserLoadingItem")
            .assertHeightIsEqualTo(20.dp)
            .assertWidthIsEqualTo(150.dp)
    }
}
