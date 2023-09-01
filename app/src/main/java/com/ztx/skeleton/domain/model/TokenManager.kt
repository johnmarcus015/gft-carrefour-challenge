package com.ztx.skeleton.domain.model

import javax.inject.Inject

class TokenManager @Inject constructor() {
    private var token: String? = null

    fun setToken(newToken: String?) {
        token = newToken
    }

    fun getToken(): String? = token
}
