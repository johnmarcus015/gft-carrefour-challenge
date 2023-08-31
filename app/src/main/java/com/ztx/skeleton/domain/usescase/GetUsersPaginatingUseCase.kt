package com.ztx.skeleton.domain.usescase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.domain.model.User
import javax.inject.Inject

class GetUsersPaginatingUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(since: Int): List<User> {
        return repository.getUsersPaginating(since)
    }
}