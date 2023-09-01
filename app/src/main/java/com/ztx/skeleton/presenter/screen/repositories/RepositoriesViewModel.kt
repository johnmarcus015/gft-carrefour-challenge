package com.ztx.skeleton.presenter.screen.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ztx.skeleton.domain.usescase.GetRepositoriesUseCase
import com.ztx.skeleton.presenter.model.mapper.toRepositoryUiData
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.ConnectionError
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.GenericError
import com.ztx.skeleton.presenter.uistate.RepositoriesUiState.RepositoriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<RepositoriesUiState>(RepositoriesUiState.Loading())
    val uiState: StateFlow<RepositoriesUiState> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getRepositories(username: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val repositories =
                    getRepositoriesUseCase.invoke(username).map { it.toRepositoryUiData() }
                _uiState.value = RepositoriesList(repositories)
            } catch (error: ConnectException) {
                _uiState.value = ConnectionError(error)
            } catch (error: Exception) {
                _uiState.value = GenericError(error)
            }
        }
        _isLoading.value = false
    }
}