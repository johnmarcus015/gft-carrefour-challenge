package com.ztx.skeleton.presenter.uistate

import com.ztx.skeleton.presenter.model.UserUiData

sealed class UsersUiState {
    class Loading : UsersUiState()

    class UserNotfound : UsersUiState()

    class UsersList(val users: List<UserUiData>) : UsersUiState()

    class ConnectionError(val error: Exception) : UsersUiState()

    class GenericError(val error: Exception) : UsersUiState()
}