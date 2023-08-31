package com.ztx.skeleton.domain.usescase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.domain.model.User
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}