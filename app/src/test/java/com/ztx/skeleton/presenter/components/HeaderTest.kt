package com.ztx.skeleton.presenter.components

import android.os.Build
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.R
import com.ztx.skeleton.utils.MainDispatcherRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class HeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `Verify if icon is displayed correctly`() {
        composeTestRule.setContent { Header() }
        val logoIcon = composeTestRule.onNodeWithContentDescription("logo icon")
        logoIcon.assertExists().assertIsDisplayed()
        logoIcon.assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun `Verify if icon is showing the correct image`() {
        composeTestRule.setContent { Header() }
        composeTestRule.onNodeWithTag(R.drawable.ic_github.toString()).assertIsDisplayed()
    }

    @Test
    fun `Verify if title is displayed correctly`() {
        composeTestRule.setContent { Header() }
        composeTestRule.onNodeWithText("Github").assertExists().assertIsDisplayed()
    }

    @Test
    fun `Verify if header has the correct dimension`() {
        composeTestRule.setContent { Header() }
        composeTestRule.onNodeWithTag("headerRow").assertHeightIsEqualTo(70.dp)
    }

    @Test
    fun `Verify if header is not showing separator when subtitle is null`() {
        composeTestRule.setContent { Header() }
        composeTestRule.onNodeWithText("|").assertDoesNotExist()
    }

    @Test
    fun `Verify if header is not showing subtitle when its null`() {
        composeTestRule.setContent { Header() }
        composeTestRule.onNodeWithText("Repositories").assertDoesNotExist()
    }

    @Test
    fun `Verify if header is showing the separator when there is a subtitle`() {
        composeTestRule.setContent {
            Header(subTitle = "Repositories")
        }
        println(composeTestRule.onRoot().printToString())
        composeTestRule.onNodeWithText("|").assertExists().assertIsDisplayed()
    }

    @Test
    fun `Verify if header is showing the subtitle when its declared`() {
        composeTestRule.setContent {
            Header(subTitle = "Repositories")
        }
        composeTestRule.onNodeWithText("Repositories").assertExists().assertIsDisplayed()
    }
}