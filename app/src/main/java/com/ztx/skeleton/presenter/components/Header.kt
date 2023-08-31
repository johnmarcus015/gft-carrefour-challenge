package com.ztx.skeleton.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ztx.skeleton.R

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 0.dp,
        bottomEnd = 45.dp
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(shape)
            .shadow(1.dp, shape)
            .background(color = Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(start = 16.dp),
            painter = painterResource(id = R.drawable.ic_github),
            contentDescription = "logo icon"
        )
        WeightedText(
            pairs = listOf(
                Pair("Git", 400),
                Pair("hub", 800)
            ),
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 12312312)
@Composable
fun HeaderPreview() {
    Header()
}