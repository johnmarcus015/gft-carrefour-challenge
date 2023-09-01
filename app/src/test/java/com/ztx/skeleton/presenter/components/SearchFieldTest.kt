package com.ztx.skeleton.presenter.components

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class SearchFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `Verify if searchField is showing placeholder correctly`() {
        composeTestRule.setContent {
            SearchField(onSearchAction = {})
        }
        composeTestRule.onNodeWithText("Search username...").assertExists()
    }

    @Test
    fun `Verify if searchField is showing the search icon correctly`() {
        composeTestRule.setContent {
            SearchField(onSearchAction = {})
        }
        composeTestRule.onNodeWithContentDescription("Search Icon").assertExists()
    }

    @Test
    fun `Verify if searchField is showing clear icon correctly`() {
        composeTestRule.setContent {
            SearchField(onSearchAction = {})
        }
        // Initial state: Clear Icon should not be visible
        composeTestRule.onNodeWithContentDescription("Clear Icon").assertDoesNotExist()

        // Enter text: Clear Icon should be visible
        composeTestRule.onNodeWithTag("inputTextSearchField")
            .performTextInput("test")

        composeTestRule.onNodeWithContentDescription("Clear Icon").assertExists()

        // Click Clear Icon: Text should be cleared and icon should disappear
        composeTestRule.onNodeWithContentDescription("Clear Icon").performClick()

        composeTestRule.onNodeWithText("test").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Clear Icon").assertDoesNotExist()
    }

    @Test
    fun `Verify if input text works correctly`() = runTest {
        composeTestRule.setContent {
            SearchField(onSearchAction = {})
        }

        composeTestRule.onNodeWithTag("inputTextSearchField")
            .performTextInput("test")

        composeTestRule.onNodeWithTag("inputTextSearchField")
            .assertTextEquals("test")
    }

    @Test
    fun `Verify if searchField is triggering onSearchAction correctly`() = runTest {
        var searchText = ""
        var actionTriggered = false

        composeTestRule.setContent {
            SearchField(onSearchAction = {
                searchText = it
                actionTriggered = true
            })
        }

        // Enter text with three characters: onSearchAction should be triggered after delay
        composeTestRule.onNodeWithTag("inputTextSearchField")
            .performTextInput("test")
        // Click on button search
        composeTestRule.onNodeWithTag("buttonSearchField").performClick()

        composeTestRule.waitForIdle()
        composeTestRule.mainClock.advanceTimeBy(1500)

        assertTrue(actionTriggered)
        assertEquals("test", searchText)
    }
}
