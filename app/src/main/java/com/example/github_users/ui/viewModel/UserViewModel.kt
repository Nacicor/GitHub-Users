package com.example.github_users.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.github_users.domain.model.User
import com.example.github_users.domain.usecase.GetUserPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val getUserPagingDataUseCase: GetUserPagingDataUseCase) :
    ViewModel() {
    val users: Flow<PagingData<User>> = getUserPagingDataUseCase.execute().cachedIn(viewModelScope)

}