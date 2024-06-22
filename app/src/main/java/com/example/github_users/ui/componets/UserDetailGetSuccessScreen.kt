package com.example.github_users.ui.componets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.github_users.R
import com.example.github_users.domain.model.UserDetail


@Composable
fun GetUserDetailSuccessScreen(userDetail: UserDetail) {
    val context = LocalContext.current
    var nameText by remember(userDetail.login) {
        mutableStateOf(
            userDetail.name ?: "N/A"
        )
    }
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
        TextField(
            value = nameText,
            onValueChange = {
                nameText = it
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = LocalContentColor.current
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
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
            Text(text = userDetail.blog ?: "N/A", modifier = Modifier.clickable {
                val mapUrl = userDetail.blog
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                context.startActivity(intent)
            }, color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
