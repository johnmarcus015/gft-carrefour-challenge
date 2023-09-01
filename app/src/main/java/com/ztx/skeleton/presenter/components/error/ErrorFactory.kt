package com.ztx.skeleton.presenter.components.error

import androidx.compose.runtime.Composable
import com.ztx.skeleton.R

object ErrorFactory {
    @Composable
    fun CreateConnectionError(message: String) {
        Error(
            message = message,
            image = R.drawable.ic_connection_error
        )
    }

    @Composable
    fun CreateGenericError(message: String) {
        Error(
            message = message,
            image = R.drawable.ic_warning
        )
    }
}