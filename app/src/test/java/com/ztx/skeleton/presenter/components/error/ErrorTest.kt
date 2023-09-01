package com.ztx.skeleton.presenter.components.error

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class ErrorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Verify if Error is showing image of connection error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateConnectionError(message = "Connection Error")
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_connection_error.toString()).assertIsDisplayed()
    }

    @Test
    fun `Verify is Error is showing message of connection error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateConnectionError(message = "Connection Error")
        }
        composeTestRule.onNodeWithText("Sorry, an unexpected error occurred! \nConnection Error")
    }

    @Test
    fun `Verify if Error is showing image of generic error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateGenericError(message = "Generic Error")
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_warning.toString()).assertIsDisplayed()
    }

    @Test
    fun `Verify is Error is showing message of generic error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateGenericError(message = "Generic Error")
        }
        composeTestRule.onNodeWithText("Sorry, an unexpected error occurred! \nGeneric Error")
    }
}