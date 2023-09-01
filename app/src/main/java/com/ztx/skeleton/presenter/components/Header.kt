package com.ztx.skeleton.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ztx.skeleton.R
import com.ztx.skeleton.presenter.ui.theme.Gray_616161

@Composable
fun Header(
    subTitle: String? = null,
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
            .background(color = Color(0xFFFFFFFF))
            .testTag("headerRow"),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val resId = R.drawable.ic_github
        Image(
            modifier = Modifier
                .size(50.dp)
                .padding(start = 16.dp)
                .testTag(resId.toString()),
            painter = painterResource(id = resId),
            contentDescription = "logo icon"
        )
        WeightedText(
            pairs = listOf(
                Pair("Git", 400),
                Pair("hub", 800)
            ),
            fontSize = 26.sp,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        )
        if (subTitle != null) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp),
                text = "|",
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                color = Gray_616161
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = subTitle,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                color = Gray_616161
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF12312312)
@Composable
fun HeaderPreview() {
    Header()
}

@Preview(showBackground = true, backgroundColor = 0xFF12312312)
@Composable
fun HeaderWithSubtitlePreview() {
    Header(
        subTitle = "Repositories"
    )
}