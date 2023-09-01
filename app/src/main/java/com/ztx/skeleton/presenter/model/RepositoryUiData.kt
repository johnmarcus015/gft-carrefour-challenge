package com.ztx.skeleton.presenter.model

data class RepositoryUiData(
    val name: String,
    val description: String? = null,
    val owner: UserUiData
)