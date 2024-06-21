package com.example.github_users.ui.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.github_users.R
import com.example.github_users.domain.model.User

@Composable
fun UserItem(user: User, index:Int , modifier: Modifier= Modifier) {
    Card(modifier = modifier
        , shape = RoundedCornerShape(0.dp) , elevation = CardDefaults.cardElevation(8.dp) ,
        border = BorderStroke(
            width = 1.dp,
        color = (if (isSystemInDarkTheme()) Color.DarkGray else Color.White)
    )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Text(text = index.toString())
            Spacer(modifier = Modifier.padding(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .decoderFactory(ImageDecoderDecoder.Factory())
                    .build()
                , contentDescription = null
                , modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape))
            Spacer(modifier = Modifier.padding(8.dp))
            Column {
                Text(text = user.login)
                if(user.siteAdmin){
                    Card {
                        Text(text = "STAFF" , modifier = Modifier
                            .background(colorResource(id = R.color.lightBlue))
                            .padding(start = 12.dp , end = 12.dp , top = 2.dp , bottom = 2.dp)
                            , color = Color.White
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun Test() {
    val testUser = User(login = "empty" , id = 123 , avatarUrl = "https://i.imgur.com/btwmswh.jpeg" , siteAdmin = true )

    UserItem(user = testUser , 1)
}