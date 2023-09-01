package com.ztx.skeleton.presenter.components.error

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ztx.skeleton.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorFactoryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Verify if errorFactory is creating a connect error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateConnectionError(message = "Connection Error")
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_connection_error.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("Sorry, an unexpected error occurred! \nConnection Error")
    }

    @Test
    fun `Verify if errorFactory is creating a generic error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateGenericError(message = "Generic Error")
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_warning.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("Sorry, an unexpected error occurred! \nGeneric Error")
    }
}