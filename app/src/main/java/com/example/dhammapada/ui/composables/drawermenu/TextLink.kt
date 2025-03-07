package com.example.dhammapada.ui.composables.drawermenu

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable
fun TextLink(
    fullText: String,
    linkText: String,
    link: String,
    textStyle: TextStyle
) {
    val context = LocalContext.current
    val annotatedText = buildAnnotatedString {
        val startIndex = fullText.indexOf(linkText)
        val endIndex = startIndex + linkText.length

        withStyle(style = textStyle.toSpanStyle()) {
            append(fullText)
        }

        if (startIndex != -1) {
            addStyle(
                style = SpanStyle(
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.Underline
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = link,
                start = startIndex,
                end = endIndex
            )
        }
    }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        context.startActivity(intent)
                    }
            },
            style = textStyle
        )
}