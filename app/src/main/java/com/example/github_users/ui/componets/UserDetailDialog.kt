package com.example.github_users.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.github_users.R
import com.example.github_users.domain.model.UserDetail

@Composable
fun UserDetailDialog(
    userDetail: UserDetail,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(userDetail.avatarUrl)
                            .decoderFactory(ImageDecoderDecoder.Factory())
                            .build(), contentDescription = null, modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = userDetail.name ?: "N/A",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = userDetail.bio ?: "N/A",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row {
                        Icon(
                            Icons.Default.AccountBox,
                            null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Column {
                            Text(
                                text = userDetail.login,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            if (userDetail.siteAdmin) {
                                Card {
                                    Text(
                                        text = "STAFF", modifier = Modifier
                                            .background(colorResource(id = R.color.lightBlue))
                                            .padding(
                                                start = 12.dp,
                                                end = 12.dp,
                                                top = 2.dp,
                                                bottom = 2.dp
                                            ), color = Color.White
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row {
                        Icon(
                            Icons.Default.LocationOn,
                            null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = userDetail.location ?: "N/A")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row {
                        Icon(
                            painterResource(id = R.drawable.link_24dp_fill0_wght400_grad0_opsz24),
                            null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = userDetail.blog ?: "N/A")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                        Text("Close")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun Test() {
    val userDetail = UserDetail(
        avatarUrl = "",
        name = "Nick",
        bio = "12345678901234567890",
        login = "Nacicor",
        siteAdmin = true,
        location = "Taiwan",
        blog = "http:XXXXXXXXXXXX",
        id = 123
    )
    UserDetailDialog(userDetail = userDetail, showDialog = true, onDismiss = {})
}