package com.ztx.skeleton.data.api

import com.ztx.skeleton.utils.JsonReaderUtils
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class GithubServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubService: GithubService

    @BeforeTest
    fun setUp() {
        mockWebServer = MockWebServer()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    @Test
    fun `Verify if getUsers make request with headers, endpoint and receive the data correctly`() =
        runTest {
            // Assert
            val jsonContent = JsonReaderUtils.readJsonFromResources("users_response_page_1.json")
            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(jsonContent.trimIndent())
            mockWebServer.enqueue(mockResponse)
            // Act
            val users = githubService.getUsers()
            // Assert
            val request = mockWebServer.takeRequest()
            assertEquals("/users", request.path)
            assertEquals(30, users.size)
            assertEquals(1, users[0].id)
            assertEquals(46, users[29].id) // .json contains 30 users
            request.getHeader("Authorization")?.let { assertTrue(it.contains("token ")) }
        }

    @Test
    fun `Verify if getUsersPaginating make request with since param and receive the data correctly`() =
        runTest {
            // Assert
            val jsonContent = JsonReaderUtils.readJsonFromResources("users_response_page_2.json")
            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(jsonContent.trimIndent())
            mockWebServer.enqueue(mockResponse)
            val since = 46
            // Act
            val newUsers = githubService.getUsersPaginating(since)
            // Assert
            val request = mockWebServer.takeRequest()
            assertEquals("/users?since=46", request.path)
            assertEquals(30, newUsers.size)
            assertEquals(47, newUsers[0].id)
            assertEquals(91, newUsers[29].id) // .json contains 30 users
            request.getHeader("Authorization")?.let { assertTrue(it.contains("token ")) }
        }

    @AfterTest
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
