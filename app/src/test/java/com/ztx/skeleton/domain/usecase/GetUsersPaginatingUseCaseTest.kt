package com.ztx.skeleton.domain.usecase

import com.ztx.skeleton.data.GithubRepository
import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.mapper.toUser
import com.ztx.skeleton.domain.usescase.GetUsersPaginatingUseCase
import com.ztx.skeleton.domain.usescase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUsersPaginatingUseCaseTest {

    private val repository = mockk<GithubRepository>()
    private val usecase = GetUsersPaginatingUseCase(repository)

    @Test
    fun `Verify if invoke is called and it is getting users paginating correctly`() = runTest {
        // Arrange
        val userResponse = UserResponse(
            login = "jnewland",
            id = 47,
            nodeId = "MDQ6VXNlcjQ3",
            avatarUrl = "https://avatars.githubusercontent.com/u/47?v=4",
            gravatarId = "",
            url = "https://api.github.com/users/jnewland",
            htmlUrl = "https://github.com/jnewland",
            followersUrl = "https://api.github.com/users/jnewland/followers",
            followingUrl = "https://api.github.com/users/jnewland/following{/other_user}",
            gistsUrl = "https://api.github.com/users/jnewland/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/jnewland/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/jnewland/subscriptions",
            organizationsUrl = "https://api.github.com/users/jnewland/orgs",
            reposUrl = "https://api.github.com/users/jnewland/repos",
            eventsUrl = "https://api.github.com/users/jnewland/events{/privacy}",
            receivedEventsUrl = "https://api.github.com/users/jnewland/received_events",
            type = "User",
            siteAdmin = false,
            starredAt = ""
        )
        val since = 46
        coEvery { repository.getUsersPaginating(since) } returns listOf(userResponse.toUser())
        // Act
        val users = usecase.invoke(since)
        // Arrange
        assertEquals(47, users[0].id)
        assertEquals("jnewland", users[0].login)
        assertEquals("https://avatars.githubusercontent.com/u/47?v=4", users[0].avatarUrl)
    }
}