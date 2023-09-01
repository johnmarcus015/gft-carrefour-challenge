package com.ztx.skeleton.presenter.uistate

import com.ztx.skeleton.presenter.model.RepositoryUiData

sealed class RepositoriesUiState {

    class Loading : RepositoriesUiState()

    data class RepositoriesList(val repositories: List<RepositoryUiData>) : RepositoriesUiState()

    data class ConnectionError(val error: Exception) : RepositoriesUiState()

    data class GenericError(val error: Exception) : RepositoriesUiState()
}