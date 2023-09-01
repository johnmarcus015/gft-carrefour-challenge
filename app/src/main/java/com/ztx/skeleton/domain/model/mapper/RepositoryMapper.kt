package com.ztx.skeleton.domain.model.mapper

import com.ztx.skeleton.data.model.RepositoryResponse
import com.ztx.skeleton.domain.model.Repository

fun RepositoryResponse.toRepository(): Repository {
    return Repository(
        name = this.name,
        description = this.description,
        owner = this.owner.toUser()
    )
}