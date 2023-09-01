package com.ztx.skeleton.presenter.components

import android.os.Build
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class WeightedTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `Verify WeightedText renders correctly`() = runTest {
        val pairs = listOf(
            Pair("Git", 400),
            Pair("hub", 800)
        )

        composeTestRule.setContent {
            WeightedText(pairs = pairs)
        }

        // Check if the entire constructed text "Github" exists
        composeTestRule.onNodeWithText("Github", useUnmergedTree = true).assertExists()

        // Additional checks can be added to ensure the individual font weights, but
        // Compose currently lacks direct utilities to assert on text style.
        // However, if the main concern is that the text is displayed, the above assertion should suffice.
    }
}