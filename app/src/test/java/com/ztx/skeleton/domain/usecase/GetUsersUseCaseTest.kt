package com.ztx.skeleton.domain.usecase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.mapper.toUser
import com.ztx.skeleton.domain.usescase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUsersUseCaseTest {

    private val repository = mockk<GithubRepository>()
    private val usecase = GetUsersUseCase(repository)

    @Test
    fun `Verify if invoke is called and it is getting users correctly`() = runTest {
        // Arrange
        val userResponse = UserResponse(
            login = "mojombo",
            id = 1,
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            url = "https://api.github.com/users/mojombo",
            htmlUrl = "https://github.com/mojombo",
            followersUrl = "https://api.github.com/users/mojombo/followers",
            followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
            gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
            organizationsUrl = "https://api.github.com/users/mojombo/orgs",
            reposUrl = "https://api.github.com/users/mojombo/repos",
            eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
            receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
            type = "User",
            siteAdmin = false,
            starredAt = ""
        )
        coEvery { repository.getUsers() } returns listOf(userResponse.toUser())
        // Act
        val users = usecase.invoke()
        // Arrange
        assertEquals(1, users[0].id)
        assertEquals("mojombo", users[0].login)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", users[0].avatarUrl)
    }
}