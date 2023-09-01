package com.ztx.skeleton.presenter.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ztx.skeleton.R
import com.ztx.skeleton.presenter.components.WeightedText
import com.ztx.skeleton.presenter.navigation.Routes
import com.ztx.skeleton.presenter.ui.theme.SkeletonTheme
import com.ztx.skeleton.presenter.utils.VersionUtils
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val versionName = VersionUtils.getAppVersion(context = LocalContext.current)

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.usersRoute) {
            popUpTo(Routes.splashscreenRoute) { inclusive = true }
        }
    }

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
                    Pair("Git", 400),
                    Pair("hub", 800)
                ),
                fontSize = 26.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
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
fun SplashScreenPreview() {
    SkeletonTheme {
        Surface {
            val navController = rememberNavController()
            SplashScreen(navController)
        }
    }
}