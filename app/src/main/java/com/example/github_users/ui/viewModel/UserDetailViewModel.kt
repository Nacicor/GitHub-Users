package com.example.github_users.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_users.domain.usecase.GetUserDetailDataUseCase
import com.example.github_users.utils.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val getUserDetailDataUseCase: GetUserDetailDataUseCase) :
    ViewModel() {
    private val _userDetail = MutableStateFlow<UserDetailState>(UserDetailState.Loading)
    val userDetail = _userDetail.asStateFlow()

    fun fetchUserDetail(userLogin: String) {
        viewModelScope.launch {
            try {
                _userDetail.value = UserDetailState.Loading
                val result = getUserDetailDataUseCase.execute(userLogin)
                result.fold(
                    onSuccess = {
                        _userDetail.value = UserDetailState.Success(it)
                    },
                    onFailure = {
                        _userDetail.value =
                            UserDetailState.Error(it.message ?: "An unexpected error occurred")
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}