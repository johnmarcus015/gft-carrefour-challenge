package com.ztx.skeleton.presenter.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ztx.skeleton.domain.usescase.GetUsersPaginatingUseCase
import com.ztx.skeleton.domain.usescase.GetUsersUseCase
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.model.mapper.toUserUiData
import com.ztx.skeleton.presenter.uistate.UsersUiState
import com.ztx.skeleton.presenter.uistate.UsersUiState.ConnectionError
import com.ztx.skeleton.presenter.uistate.UsersUiState.GenericError
import com.ztx.skeleton.presenter.uistate.UsersUiState.Loading
import com.ztx.skeleton.presenter.uistate.UsersUiState.UsersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersPaginatingUseCase: GetUsersPaginatingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersUiState>(Loading())
    val uiState: StateFlow<UsersUiState> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val users = getUsersUseCase.invoke().map { it.toUserUiData() }
                _uiState.value = UsersList(users)
            } catch (error: ConnectException) {
                _uiState.value = ConnectionError(error)
            } catch (error: Exception) {
                _uiState.value = GenericError(error)
            }
        }
        _isLoading.value = false
    }

    fun getUsersPaginating(lastUserId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val currentUsers = (_uiState.value as? UsersList)?.users ?: emptyList()
                val newUsers: List<UserUiData> =
                    getUsersPaginatingUseCase.invoke(lastUserId).map { it.toUserUiData() }
                _uiState.value = UsersList(users = currentUsers + newUsers)
            } catch (error: ConnectException) {
                _uiState.value = ConnectionError(error)
            } catch (error: Exception) {
                _uiState.value = GenericError(error)
            }
            _isLoading.value = false
        }
    }
}