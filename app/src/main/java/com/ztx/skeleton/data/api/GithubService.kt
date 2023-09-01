package com.ztx.skeleton.data.api

import com.ztx.skeleton.data.model.RepositoryResponse
import com.ztx.skeleton.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

sealed interface GithubService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("users")
    suspend fun getUsersPaginating(
        @Query("since") since: Int
    ): List<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String
    ): List<RepositoryResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): UserResponse
}