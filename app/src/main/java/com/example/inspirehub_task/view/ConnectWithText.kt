package com.example.inspirehub_task.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inspirehub_task.viewmodel.StoryViewModel

@Composable
fun Link(viewModel: StoryViewModel = viewModel()){
    val annotatedLinkString =  buildAnnotatedString {

        val str = "اضغط هنا لمشاهده جميع القصص"
        val startIndex = str.indexOf("هنا")
        val endIndex = startIndex + 3
        append(str)
        addStyle(
            style = SpanStyle(
                color = viewModel.uiState.value.textColor.toColor(),
                fontSize=  viewModel.uiState.value.fontSize.sp,
            ), start = 0, end = str.length
        )
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize=  viewModel.uiState.value.fontSize.sp,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )

        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.google.com",
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        style = TextStyle(
            color = viewModel.uiState.value.textColor.toColor(),
            fontSize=  viewModel.uiState.value.fontSize.sp,
        ),
        modifier = Modifier
            .padding(16.dp),
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}
