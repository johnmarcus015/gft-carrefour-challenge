package com.ztx.skeleton.domain.usescase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.domain.model.Repository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(username: String): List<Repository> {
        return repository.getRepositories(username)
    }
}