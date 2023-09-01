package com.ztx.skeleton.presenter.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ztx.skeleton.R
import com.ztx.skeleton.presenter.components.WeightedText
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329
import com.ztx.skeleton.presenter.ui.theme.White_f6f8fa
import com.ztx.skeleton.presenter.utils.VersionUtils

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_github),
                contentDescription = "logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            WeightedText(
                pairs = listOf(
                    Pair("Sign", 400),
                    Pair("In", 800)
                ),
                fontSize = 26.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Click in Login to authenticate", color = Dark_1f2329)
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    onSignInClick()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Dark_1f2329,
                    contentColor = White_f6f8fa
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 48.dp, end = 48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(White_f6f8fa),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Login with GitHub", color = White_f6f8fa)
            }
        }

        val versionName = VersionUtils.getAppVersion(context = LocalContext.current)

        Text(
            text = versionName,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        onSignInClick = {}
    )
}