package com.ztx.skeleton.domain.mapper

import com.ztx.skeleton.data.model.UserResponse
import com.ztx.skeleton.domain.model.mapper.toUser
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {
    @Test
    fun `Verify if mapper is converting UserResponse to User correctly`() {
        // Assert
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
        // Act
        val user = userResponse.toUser()
        // Assert
        assertEquals(1, user.id)
        assertEquals("mojombo", user.login)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", user.avatarUrl)
    }
}