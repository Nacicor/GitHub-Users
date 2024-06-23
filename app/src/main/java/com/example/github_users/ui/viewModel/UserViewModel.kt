package com.example.github_users.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github_users.domain.model.User
import com.example.github_users.domain.usecase.GetUserPagingDataUseCase
import kotlinx.coroutines.flow.Flow

class UserViewModel(private val getUserPagingDataUseCase: GetUserPagingDataUseCase) : ViewModel() {
    val users: Flow<PagingData<User>> = getUserPagingDataUseCase.execute().cachedIn(viewModelScope)

}