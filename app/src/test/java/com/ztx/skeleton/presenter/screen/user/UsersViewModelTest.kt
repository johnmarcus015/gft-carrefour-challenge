package com.ztx.skeleton.presenter.screen.user

import com.google.common.truth.Truth.assertThat
import com.ztx.skeleton.domain.usescase.GetUserUseCase
import com.ztx.skeleton.domain.usescase.GetUsersPaginatingUseCase
import com.ztx.skeleton.domain.usescase.GetUsersUseCase
import com.ztx.skeleton.mocks.Mocker
import com.ztx.skeleton.presenter.screen.users.UsersViewModel
import com.ztx.skeleton.presenter.uistate.UsersUiState
import com.ztx.skeleton.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.ConnectException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUserUseCase = mockk<GetUserUseCase>()
    private val getUsersUseCase = mockk<GetUsersUseCase>()
    private val getUserPaginatingUseCase = mockk<GetUsersPaginatingUseCase>()

    private lateinit var viewModel: UsersViewModel

    @Before
    fun setUp() {
        viewModel = UsersViewModel(getUsersUseCase, getUserPaginatingUseCase, getUserUseCase)
    }

    @Test
    fun `Verify if uiState is loading when viewModel is started`() = runTest {
        // Assert
        val uiState = viewModel.uiState.value
        assertThat(uiState).isInstanceOf(UsersUiState.Loading::class.java)
    }

    @Test
    fun `Verify if uiState is error when getUsers throw connect exception`() {
        // Arrange
        coEvery { getUsersUseCase.invoke() } throws UnknownHostException()
        // Act
        viewModel.getUsers()
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(UsersUiState.ConnectionError::class.java)
    }

    @Test
    fun `Verify if uiState is success when getUsers is called`() {
        // Arrange
        val users = Mocker.createUsers("users_response_page_1.json")
        coEvery { getUsersUseCase.invoke() } returns users
        // Assert
        var uiState = viewModel.uiState.value
        assertThat(uiState).isInstanceOf(UsersUiState.Loading::class.java)
        // Act
        viewModel.getUsers()
        // Assert
        uiState = viewModel.uiState.value
        assertThat(uiState).isInstanceOf(UsersUiState.UsersList::class.java)
        val actualUsers = (uiState as UsersUiState.UsersList).users
        assertEquals(30, actualUsers.size)
    }

    @Test
    fun `Verify if uiState is generic error when getUsers throw Exception`() {
        // Arrange
        coEvery { getUsersUseCase.invoke() } throws Exception()
        // Act
        viewModel.getUsers()
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(UsersUiState.GenericError::class.java)
    }

    @Test
    fun `Verify if uiState is connection error when getUsers throw ConnectException`() {
        // Arrange
        coEvery { getUsersUseCase.invoke() } throws UnknownHostException()
        // Act
        viewModel.getUsers()
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(UsersUiState.ConnectionError::class.java)
    }

    @Test
    fun `Verify if getUsers is getting the users correctly`() {
        // Arrange
        val users = Mocker.createUsers("users_response_page_1.json")
        coEvery { getUsersUseCase.invoke() } returns users
        // Act
        viewModel.getUsers()
        val actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(30, actualUsers.size)
        assertEquals(1, actualUsers[0].id)
        assertEquals("mojombo", actualUsers[0].login)
        assertEquals(
            "https://avatars.githubusercontent.com/u/1?v=4", actualUsers[0].avatarUrl
        )
        assertEquals(46, actualUsers[29].id)
        assertEquals("bmizerany", actualUsers[29].login)
        assertEquals(
            "https://avatars.githubusercontent.com/u/46?v=4", actualUsers[29].avatarUrl
        )
    }

    @Test
    fun `Verify if pagination is incrementing the users correctly`() {
        // Arrange
        val lastUserId = 46
        val usersFromPage1 = Mocker.createUsers("users_response_page_1.json")
        val usersFromPage2 = Mocker.createUsers("users_response_page_2.json")
        coEvery { getUsersUseCase.invoke() } returns usersFromPage1
        coEvery { getUserPaginatingUseCase.invoke(lastUserId) } returns usersFromPage2
        // Act
        viewModel.getUsers()
        var actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(30, actualUsers.size)
        assertEquals(1, actualUsers[0].id)
        assertEquals(46, actualUsers[29].id)
        // Act
        viewModel.getUsersPaginating(lastUserId)
        actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(60, actualUsers.size)
        assertEquals(47, actualUsers[30].id)
        assertEquals(91, actualUsers[59].id)
    }

    @Test
    fun `Verify if getUsersPaginating is getting the users correctly`() {
        // Arrange
        val lastUserId = 46
        val users = Mocker.createUsers("users_response_page_2.json")
        coEvery { getUserPaginatingUseCase.invoke(lastUserId) } returns users
        // Act
        viewModel.getUsersPaginating(lastUserId)
        val actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(30, actualUsers.size)
        assertEquals(47, actualUsers[0].id)
        assertEquals("jnewland", actualUsers[0].login)
        assertEquals(
            "https://avatars.githubusercontent.com/u/47?v=4",
            actualUsers[0].avatarUrl
        )
        assertEquals(91, actualUsers[29].id)
        assertEquals("lifo", actualUsers[29].login)
        assertEquals(
            "https://avatars.githubusercontent.com/u/91?v=4",
            actualUsers[29].avatarUrl
        )
    }

    @Test
    fun `Verify if long pagination is incrementing the users correctly`() {
        // Arrange
        val lastUserIdFromPagination1 = 46
        val lastUserIdFromPagination2 = 91
        val usersFromPage1 = Mocker.createUsers("users_response_page_1.json")
        val usersFromPage2 = Mocker.createUsers("users_response_page_2.json")
        val usersFromPage3 = Mocker.createUsers("users_response_page_3.json")
        coEvery { getUsersUseCase.invoke() } returns usersFromPage1
        coEvery { getUserPaginatingUseCase.invoke(lastUserIdFromPagination1) } returns usersFromPage2
        // Act
        viewModel.getUsers()
        var actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(30, actualUsers.size)
        assertEquals(1, actualUsers[0].id)
        assertEquals(46, actualUsers[29].id)
        // Act
        viewModel.getUsersPaginating(lastUserIdFromPagination1)
        actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(60, actualUsers.size)
        assertEquals(47, actualUsers[30].id)
        assertEquals(91, actualUsers[59].id)
        // Arrange
        coEvery { getUserPaginatingUseCase.invoke(lastUserIdFromPagination2) } returns usersFromPage3
        // Act
        viewModel.getUsersPaginating(lastUserIdFromPagination2)
        actualUsers = (viewModel.uiState.value as UsersUiState.UsersList).users
        // Assert
        assertEquals(90, actualUsers.size)
        assertEquals(91, actualUsers[59].id)
        assertEquals(125, actualUsers[89].id)
    }

    @Test
    fun `Verify if uiState is error when getUsersPaginating throw connect exception`() {
        // Arrange
        val lastUserId = 46
        coEvery { getUserPaginatingUseCase.invoke(lastUserId) } throws UnknownHostException()
        // Act
        viewModel.getUsersPaginating(lastUserId)
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(UsersUiState.ConnectionError::class.java)
    }

    @Test
    fun `Verify if getUser is getting a user correctly`() {
        // Arrange
        val username = "username"
        val user = Mocker.createUsers("users_response_page_1.json")[0]
        coEvery { getUserUseCase.invoke(username) } returns user
        // Act
        viewModel.getUser(username)
        val currentUser = (viewModel.uiState.value as UsersUiState.User).user
        // Assert
        assertEquals(1, currentUser.id)
        assertEquals("mojombo", currentUser.login)
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", currentUser.avatarUrl)
    }

    @Test
    fun `Verify if isLoading is false when viewModel is initialized`() {
        // Assert
        assertEquals(false, viewModel.isLoading.value)
    }
}