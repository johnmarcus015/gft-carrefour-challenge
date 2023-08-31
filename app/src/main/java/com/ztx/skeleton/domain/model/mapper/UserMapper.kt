package com.ztx.skeleton.domain.model.mapper

import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.User

fun UserResponse.toUser(): User {
    return User(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatarUrl
    )
}