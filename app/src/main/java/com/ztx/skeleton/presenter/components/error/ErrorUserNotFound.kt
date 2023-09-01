package com.ztx.skeleton.presenter.components.error

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ztx.skeleton.R
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329
import com.ztx.skeleton.presenter.ui.theme.White_f6f8fa

@Composable
fun ErrorUserNotFound(
    buttonText: String,
    onClickButton: () -> Unit,
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
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onClickButton() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Dark_1f2329,
                contentColor = White_f6f8fa
            ),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .height(56.dp)  // assuming you want to match the height of the OutlinedTextField
        ) {
            Text(text = buttonText, color = White_f6f8fa)
        }
    }
}

@Preview
@Composable
fun ErrorErrorUserNotFoundPreview() {
    ErrorUserNotFound(
        buttonText = "Reload the main screen",
        onClickButton = {}
    )
}