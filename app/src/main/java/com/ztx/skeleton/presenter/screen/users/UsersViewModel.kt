package com.ztx.skeleton.presenter.screen.users

import android.content.res.Resources.NotFoundException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ztx.skeleton.domain.usescase.GetUserUseCase
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
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersPaginatingUseCase: GetUsersPaginatingUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersUiState>(Loading())
    val uiState: StateFlow<UsersUiState> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private lateinit var users: List<UserUiData>

    fun getUser(username: String) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase.invoke(username).toUserUiData()
                _uiState.value = UsersUiState.User(user)
            } catch (error: UnknownHostException) {
                _uiState.value = ConnectionError(error)
            } catch (error: Exception) {
                if (error is HttpException && error.code() == 404) {
                    _uiState.value = UsersUiState.UserNotfound()
                } else {
                    _uiState.value = GenericError(error)
                }
            }
        }
    }

    fun getUsers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                users = getUsersUseCase.invoke().map { it.toUserUiData() }
                _uiState.value = UsersList(users)
            } catch (error: UnknownHostException) {
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
                users = currentUsers + newUsers
                _uiState.value = UsersList(users = users)
            } catch (error: UnknownHostException) {
                _uiState.value = ConnectionError(error)
            } catch (error: Exception) {
                _uiState.value = GenericError(error)
            }
            _isLoading.value = false
        }
    }
}