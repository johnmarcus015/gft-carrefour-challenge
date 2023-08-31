package com.ztx.skeleton.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.User
import com.ztx.skeleton.domain.model.mapper.toUser
import com.ztx.skeleton.utils.JsonReaderUtils

object MockUsers {
    fun createMock(fileWithMockData: String): List<User> {
        val jsonContent = JsonReaderUtils.readJsonFromResources(fileWithMockData)
        val listType = object : TypeToken<List<UserResponse>>() {}.type
        val gson = Gson()
        val usersResponse = gson.fromJson<List<UserResponse>>(jsonContent, listType)
        return usersResponse.map { it.toUser() }
    }
}