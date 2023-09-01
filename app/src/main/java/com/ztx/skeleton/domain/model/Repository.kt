package com.ztx.skeleton.domain.model

data class Repository(
    val name: String,
    val description: String?,
    val owner: User
)
