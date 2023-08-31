package com.ztx.skeleton.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ztx.skeleton.data.api.GithubService
import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.mapper.toUser
import com.ztx.skeleton.utils.JsonReaderUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GithubRepositoryTest {

    private val service = mockk<GithubService>()
    private lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        repository = GithubRepository(service)
    }

    @Test
    fun `Verify if getUsers is returning users correctly`() = runTest {
        // Arrange
        val jsonContent = JsonReaderUtils.readJsonFromResources("users_response_page_1.json")
        val listType = object : TypeToken<List<UserResponse>>() {}.type
        val gson = Gson()
        val apiResponse = gson.fromJson<List<UserResponse>>(jsonContent, listType)
        val expectedUsers = apiResponse.map { it.toUser() }
        coEvery { service.getUsers() } returns apiResponse
        // Act
        val actualUsers = repository.getUsers()
        // Assert
        assertEquals(expectedUsers, actualUsers)
        assertEquals(30, actualUsers.size)
        assertEquals(1, actualUsers[0].id)
        assertEquals("mojombo", actualUsers[0].login)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", actualUsers[0].avatarUrl)
        assertEquals(46, actualUsers[29].id)
        assertEquals("bmizerany", actualUsers[29].login)
        assertEquals("https://avatars.githubusercontent.com/u/46?v=4", actualUsers[29].avatarUrl)
    }

    @Test
    fun `Verify if getUsersPaginating is returning users correctly`() = runTest {
        // Arrange
        val jsonContent = JsonReaderUtils.readJsonFromResources("users_response_page_2.json")
        val listType = object : TypeToken<List<UserResponse>>() {}.type
        val gson = Gson()
        val apiResponse = gson.fromJson<List<UserResponse>>(jsonContent, listType)
        val expectedUsers = apiResponse.map { it.toUser() }
        val since = 46
        coEvery { service.getUsersPaginating(since) } returns apiResponse
        // Act
        val actualUsers = repository.getUsersPaginating(since)
        // Assert
        assertEquals(expectedUsers, actualUsers)
        assertEquals(30, actualUsers.size)
        assertEquals(47, actualUsers[0].id)
        assertEquals("jnewland", actualUsers[0].login)
        assertEquals("https://avatars.githubusercontent.com/u/47?v=4", actualUsers[0].avatarUrl)
        assertEquals(91, actualUsers[29].id)
        assertEquals("lifo", actualUsers[29].login)
        assertEquals("https://avatars.githubusercontent.com/u/91?v=4", actualUsers[29].avatarUrl)
    }
}