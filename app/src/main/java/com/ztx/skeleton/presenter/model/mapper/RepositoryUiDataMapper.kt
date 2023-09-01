package com.ztx.skeleton.presenter.model.mapper

import com.ztx.skeleton.domain.model.Repository
import com.ztx.skeleton.presenter.model.RepositoryUiData

fun Repository.toRepositoryUiData(): RepositoryUiData {
    return RepositoryUiData(
        name = this.name,
        description = this.description,
        owner = this.owner.toUserUiData()
    )
}