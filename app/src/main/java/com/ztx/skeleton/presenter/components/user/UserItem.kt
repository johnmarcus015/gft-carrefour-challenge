package com.ztx.skeleton.presenter.components.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(
    user: UserUiData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            onClick()
        },
        modifier = Modifier.testTag("cardUserItem")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .testTag("avatarImageUserItem"),
                placeholder = ColorPainter(
                    Color.LightGray
                ),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp, bottom = 15.dp, start = 16.dp),
                    text = "${user.id}. ${user.login}",
                    fontSize = 18.sp,
                    color = Dark_1f2329
                )
            }
        }
    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        user = UserUiData(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
        ),
        onClick = {}
    )
}