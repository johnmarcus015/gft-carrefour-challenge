package com.ztx.skeleton.presenter.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ztx.skeleton.presenter.components.ShimmerAnimationState.*
import kotlinx.coroutines.delay

@Composable
fun ShimmeringBox(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {
    ShimmerAnimation {
        Box(
            modifier = modifier
                .size(width, height)
                .background(brush = it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center) {
        ShimmeringBox(width = 200.dp, height = 100.dp)
    }
}


@Composable
fun ShimmerAnimation(
    colors: List<Color> = listOf(
        Color.Gray.copy(0.9f),
        Color.Gray.copy(0.2f),
        Color.Gray.copy(0.9f)
    ),
    duration: Int = 1000,
    content: @Composable (brush: Brush) -> Unit
) {
    var transitionState by remember { mutableStateOf(Start) }
    val transition = updateTransition(transitionState)

    val translateAnim by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = duration, easing = LinearEasing)
        }, label = ""
    ) { state ->
        when (state) {
            Start -> 0f
            End -> 1f
        }
    }

    val brush = Brush.linearGradient(
        colors,
        start = Offset(10f, 10f),
        end = Offset(10f - 2000f * translateAnim, 10f + 2000f * translateAnim)
    )
    Layout(
        content = { content(brush) }, // Passing default color, you can adjust this.
        measurePolicy = { measurables, constraints ->
            val placeable = measurables.first().measure(constraints)

            // Calculate end points for the gradient considering size of the component
            val width = placeable.width.toFloat()
            val height = placeable.height.toFloat()
            val brush = Brush.linearGradient(
                colors,
                start = Offset(-width, -height),
                end = Offset(width * translateAnim, height * translateAnim)
            )

            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        }
    )
                content(brush)



    LaunchedEffect(Unit) {
        while (true) {
            transitionState = End
            delay(duration.toLong())
            transitionState = Start
            delay(duration.toLong())
        }
    }
    //content(brush)
}

private enum class ShimmerAnimationState {
    Start, End
}
