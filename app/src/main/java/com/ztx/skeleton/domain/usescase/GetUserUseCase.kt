package com.ztx.skeleton.domain.usescase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(username: String): User {
        return repository.getUser(username)
    }
}