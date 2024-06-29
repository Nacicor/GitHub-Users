package com.example.github_users.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.github_users.domain.model.User
import com.example.github_users.ui.componets.UserDetailDialog
import com.example.github_users.ui.componets.UserItem
import com.example.github_users.ui.viewModel.UserDetailViewModel
import com.example.github_users.ui.viewModel.UserViewModel

@Composable
fun UserListScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    userDetailViewModel: UserDetailViewModel = hiltViewModel()
) {
    val users = userViewModel.users.collectAsLazyPagingItems()
    val userDetailState = userDetailViewModel.userDetail.collectAsState()
    var showDialog by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        items(
            count = users.itemCount,
            key = users.itemKey { it.id },
        ) { index: Int ->
            if (index < 100) {
                val user: User? = users[index]
                user?.let {
                    val modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            userDetailViewModel.fetchUserDetail(it.login)
                            showDialog = true
                        }

                    UserItem(user = it, index + 1, modifier)
                }
            }
        }
        users.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = users.loadState.refresh as LoadState.Error
                    item {
                        Text(
                            text = "Error: ${e.error.localizedMessage}",
                            color = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = users.loadState.append as LoadState.Error
                    item {
                        Text(
                            text = "Error: ${e.error.localizedMessage}",
                            color = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                }
            }
        }
    }
    userDetailState.value?.let {
        UserDetailDialog(
            userDetailState = it,
            showDialog = showDialog,
            onDismiss = {
                showDialog = false
            }
        )
    }
}

