package com.ztx.skeleton.mocks

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ztx.skeleton.data.model.RepositoryResponse
import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.Repository
import com.ztx.skeleton.domain.model.User
import com.ztx.skeleton.domain.model.mapper.toRepository
import com.ztx.skeleton.domain.model.mapper.toUser
import com.ztx.skeleton.utils.JsonReaderUtils

object Mocker {
    fun createUsers(fileWithMockData: String): List<User> {
        val jsonContent = JsonReaderUtils.readJsonFromResources(fileWithMockData)
        val listType = object : TypeToken<List<UserResponse>>() {}.type
        val gson = Gson()
        val usersResponse = gson.fromJson<List<UserResponse>>(jsonContent, listType)
        return usersResponse.map { it.toUser() }
    }

    fun createRepositories(fileWithMockData: String): List<Repository> {
        val jsonContent = JsonReaderUtils.readJsonFromResources(fileWithMockData)
        val listType = object : TypeToken<List<RepositoryResponse>>() {}.type
        val gson = Gson()
        val repositoriesResponse = gson.fromJson<List<RepositoryResponse>>(jsonContent, listType)
        return repositoriesResponse.map { it.toRepository() }
    }
}