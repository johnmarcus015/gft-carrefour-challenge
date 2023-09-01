package com.ztx.skeleton.presenter.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ztx.skeleton.presenter.ui.theme.Dark_1f2329

@Composable
fun WeightedText(
    pairs: List<Pair<String, Int>>,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    val styledText = buildAnnotatedString {
        pairs.forEach { (text, weight) ->
            withStyle(
                style = SpanStyle(
                    fontSize = fontSize,
                    fontWeight = FontWeight(weight)
                )
            ) {
                append(text)
            }
        }
    }
    Text(text = styledText, color = Dark_1f2329, modifier = modifier)
}

@Preview
@Composable
fun WeightedTextPreview() {
    WeightedText(
        listOf(
            Pair("Git", 400),
            Pair("hub", 800)
        )
    )
}