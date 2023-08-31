package com.ztx.skeleton.presenter.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ztx.skeleton.R

@Composable
fun ErrorUserNotFound(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_user_not_found),
            contentDescription = "error icon"
        )
        Text(
            text = "User not found! \nVerify if you typed the username correctly",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ErrorErrorUserNotFoundPreview() {
    ErrorUserNotFound()
}