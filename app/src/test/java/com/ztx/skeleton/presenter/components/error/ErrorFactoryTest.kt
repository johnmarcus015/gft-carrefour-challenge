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
            ErrorFactory.CreateConnectionError(onClickButton = {})
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_connection_error.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("An unexpected error occurred! \nWithout connection with internet")
    }

    @Test
    fun `Verify if errorFactory is creating a generic error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateGenericError(message = "Generic Error", onClickButton = {})
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_warning.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("An unexpected error occurred! \nGeneric Error")
    }

    @Test
    fun `Verify if errorFactory is creating a user not found error correctly`() {
        composeTestRule.setContent {
            ErrorFactory.CreateUserNotFoundError(onClickButton = {})
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_user_not_found.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("An unexpected error occurred! \nUser not found")
    }
}