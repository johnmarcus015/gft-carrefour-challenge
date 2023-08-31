package com.ztx.skeleton.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ztx.skeleton.domain.usescase.GetUsersPaginatingUseCase
import com.ztx.skeleton.domain.usescase.GetUsersUseCase
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.model.mapper.toUserUiData
import com.ztx.skeleton.presenter.uistate.UiStateHome
import com.ztx.skeleton.presenter.uistate.UiStateHome.StateHomeError
import com.ztx.skeleton.presenter.uistate.UiStateHome.StateHomeLoading
import com.ztx.skeleton.presenter.uistate.UiStateHome.StateHomeListUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersPaginatingUseCase: GetUsersPaginatingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiStateHome>(StateHomeLoading())
    val uiState: StateFlow<UiStateHome> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val users = getUsersUseCase.invoke().map { it.toUserUiData() }
                _uiState.value = StateHomeListUsers(users = users)
            } catch (e: ConnectException) {
                _uiState.value = StateHomeError(error = e)
            } catch (e: Exception) {
                _uiState.value = StateHomeError(error = e)
            }
        }
        _isLoading.value = false
    }

    fun getUsersPaginating(lastUserId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val currentUsers = (_uiState.value as? StateHomeListUsers)?.users ?: emptyList()
                val newUsers: List<UserUiData> = getUsersPaginatingUseCase.invoke(lastUserId).map { it.toUserUiData() }
                _uiState.value = StateHomeListUsers(users = currentUsers + newUsers)
            } catch (e: ConnectException) {
                _uiState.value = StateHomeError(e)
            } catch (e: Exception) {
                _uiState.value = StateHomeError(e)
            }
            _isLoading.value = false
        }
    }
}