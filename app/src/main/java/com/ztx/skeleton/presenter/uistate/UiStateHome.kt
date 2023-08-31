package com.ztx.skeleton.presenter.uistate

import com.ztx.skeleton.presenter.model.UserUiData

sealed class UiStateHome {
    class StateHomeLoading : UiStateHome()

    class StateHomeUserNotfound : UiStateHome()

    class StateHomeListUsers(
        val users: List<UserUiData>
    ) : UiStateHome()

    class StateHomeError(
        val error: Exception
    ) : UiStateHome()
}