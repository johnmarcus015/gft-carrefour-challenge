package com.ztx.skeleton.presenter.components.repository

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ztx.skeleton.presenter.model.RepositoryUiData
import com.ztx.skeleton.presenter.model.UserUiData
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329
import com.ztx.skeleton.presenter.ui.theme.Gray_616161

@Composable
fun RepositoryItem(
    repository: RepositoryUiData,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            AsyncImage(
                model = repository.owner.avatarUrl,
                contentDescription = "owner's image of repository",
                modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {},
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
                        .padding(start = 8.dp, end = 8.dp),
                    text = repository.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Dark_1f2329
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    text = repository.description ?: "Repository without description",
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
                    color = Gray_616161
                )
            }
        }
    }
}

@Preview
@Composable
fun RepositoryItemPreview() {
    RepositoryItem(
        repository = RepositoryUiData(
            name = "Retrofit",
            description = "Library to make http requests easily in android",
            owner = UserUiData(
                id = 1,
                login = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/424443?v=4"
            )
        )
    )
}