package com.ztx.skeleton.presenter.model.mapper

import com.ztx.skeleton.domain.model.User
import com.ztx.skeleton.presenter.model.UserUiData

fun User.toUserUiData(): UserUiData {
    return UserUiData(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatarUrl
    )
}