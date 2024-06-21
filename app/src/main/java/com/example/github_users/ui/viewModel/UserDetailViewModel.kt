package com.example.github_users.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_users.domain.model.UserDetail
import com.example.github_users.domain.usecase.GetUserDetailDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(private val getUserDetailDataUseCase: GetUserDetailDataUseCase):ViewModel() {
    private val _userDetail = MutableStateFlow<UserDetail?>(null)
    val userDetail = _userDetail.asStateFlow()

    fun fetchUserDetail(userLogin: String) {
        viewModelScope.launch {
            val detail = getUserDetailDataUseCase.execute(userLogin)
            _userDetail.value = detail
        }
    }
}