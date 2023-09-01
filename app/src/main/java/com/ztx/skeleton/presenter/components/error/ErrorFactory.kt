package com.ztx.skeleton.presenter.components.error

import androidx.compose.runtime.Composable
import com.ztx.skeleton.R

object ErrorFactory {
    @Composable
    fun CreateConnectionError(
        onClickButton: () -> Unit
    ) {
        Error(
            subtitle = "Without connection with internet",
            image = R.drawable.ic_connection_error,
            onClickButton = onClickButton
        )
    }

    @Composable
    fun CreateGenericError(
        message: String,
        onClickButton: () -> Unit
    ) {
        Error(
            subtitle = message,
            image = R.drawable.ic_warning,
            onClickButton = onClickButton
        )
    }

    @Composable
    fun CreateUserNotFoundError(
        onClickButton: () -> Unit
    ) {
        Error(
            title = "User not found",
            subtitle = "Verify if you typed the username correctly",
            image = R.drawable.ic_user_not_found,
            onClickButton = onClickButton
        )
    }
}