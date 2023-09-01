package com.ztx.skeleton.presenter.components.error

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ztx.skeleton.R

@Composable
fun Error(
    message: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .testTag(image.toString()),
            painter = painterResource(id = image),
            contentDescription = "error icon"
        )
        Text(
            text = "Sorry, an unexpected error occurred! \n$message",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorConnectionPreview() {
    Error(
        message = "ConnectionError",
        image = R.drawable.ic_connection_error
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorGenericPreview() {
    Error(
        message = "GenericError",
        image = R.drawable.ic_warning
    )
}