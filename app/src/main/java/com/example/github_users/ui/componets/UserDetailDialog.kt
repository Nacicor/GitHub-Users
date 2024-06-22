package com.example.github_users.ui.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.github_users.utils.UserDetailState

@Composable
fun UserDetailDialog(
    userDetailState: UserDetailState,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {

    if (showDialog) {

        Dialog(onDismissRequest = onDismiss) {
            Card {
                when (userDetailState) {
                    is UserDetailState.Loading -> {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UserDetailState.Success -> {
                        val userDetail = userDetailState.userDetail
                        GetUserDetailSuccessScreen(userDetail = userDetail)
                    }

                    is UserDetailState.Error -> {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = userDetailState.message,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}