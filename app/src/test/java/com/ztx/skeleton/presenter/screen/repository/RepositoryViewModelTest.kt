package com.ztx.skeleton.presenter.screen.repository

import com.google.common.truth.Truth.assertThat
import com.ztx.skeleton.domain.usescase.GetRepositoriesUseCase
import com.ztx.skeleton.mocks.Mocker
import com.ztx.skeleton.presenter.screen.repositories.RepositoriesViewModel
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState
import com.ztx.skeleton.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.ConnectException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RepositoriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getRepositoriesUseCase = mockk<GetRepositoriesUseCase>()
    private lateinit var viewModel: RepositoriesViewModel

    @Before
    fun setUp() {
        viewModel = RepositoriesViewModel(getRepositoriesUseCase)
    }

    @Test
    fun `Verify if uiState is loading when viewModel is started`() = runTest {
        // Assert
        val uiState = viewModel.uiState.value
        assertThat(uiState).isInstanceOf(RepositoriesUiState.Loading::class.java)
    }

    @Test
    fun `Verify if getRepositories is getting the users correctly`() {
        // Arrange
        val repositories = Mocker.createRepositories("repositories_response.json")
        val username = "username"
        coEvery { getRepositoriesUseCase.invoke(username) } returns repositories
        // Act
        viewModel.getRepositories(username)
        val actualRepositories =
            (viewModel.uiState.value as RepositoriesUiState.RepositoriesList).repositories
        // Assert
        assertEquals(7, actualRepositories.size)
        assertEquals("libdc-for-dirk", actualRepositories[0].name)
        assertEquals(
            "Only use for syncing with Dirk, don't use for anything else",
            actualRepositories[0].description
        )
        assertEquals("uemacs", actualRepositories[6].name)
        assertEquals(
            "Random version of microemacs with my private modificatons",
            actualRepositories[6].description
        )
    }

    @Test
    fun `Verify if uiState is generic error when getRepositories throw Exception`() = runTest {
        // Arrange
        val username = "username"
        coEvery { getRepositoriesUseCase.invoke(username) } throws Exception()
        // Act
        viewModel.getRepositories(username)
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(RepositoriesUiState.GenericError::class.java)
    }

    @Test
    fun `Verify if uiState is connection error when getRepositories throw ConnectException`() {
        // Arrange
        val username = "username"
        coEvery { getRepositoriesUseCase.invoke(username) } throws ConnectException()
        // Act
        viewModel.getRepositories(username)
        // Assert
        assertThat(viewModel.uiState.value).isInstanceOf(RepositoriesUiState.ConnectionError::class.java)
    }
}
