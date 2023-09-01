package com.ztx.skeleton.data

import com.ztx.skeleton.data.api.GithubService
import com.ztx.skeleton.domain.model.Repository
import com.ztx.skeleton.domain.model.User
import com.ztx.skeleton.domain.model.mapper.toRepository
import com.ztx.skeleton.domain.model.mapper.toUser
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val service: GithubService
) {
    suspend fun getUsers(): List<User> {
        return service.getUsers().map { it.toUser() }
    }

    suspend fun getUsersPaginating(since: Int): List<User> {
        return service.getUsersPaginating(since).map { it.toUser() }
    }

    suspend fun getRepositories(username: String): List<Repository> {
        return service.getRepositories(username).map { it.toRepository() }
    }

    suspend fun getUser(username: String): User {
        return service.getUser(username).toUser()
    }
}