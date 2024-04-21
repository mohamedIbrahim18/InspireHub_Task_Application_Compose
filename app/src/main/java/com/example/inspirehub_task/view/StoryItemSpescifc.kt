package com.example.inspirehub_task.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inspirehub_task.viewmodel.StoryViewModel
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import kotlin.random.Random

@Composable
fun StoryItem(title:String, content:String, viewModel: StoryViewModel = viewModel(), index:Int) {
val random = randomColor()
    Column {
            SelectionContainer {
                Text(text = title,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                        fontSize = viewModel.uiState.value.fontSize.sp,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                    style = LocalTextStyle.current.copy(color = random)
                    )
            }
        val annotatedLinkString =  buildAnnotatedString {
            val str = content
            val size = ((title.substring(0,title.indexOf(" ")))).length
            val startIndex = str.indexOf(title.substring(0,title.indexOf(" ")))
            val endIndex = startIndex +size
            append(str)
            addStyle(
                style = SpanStyle(
                    color = viewModel.uiState.value.textColor.toColor(),
                    fontSize=  viewModel.uiState.value.fontSize.sp,
                ), start = 0, end = str.length
            )
            addStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize=  viewModel.uiState.value.fontSize.sp,
                    textDecoration = TextDecoration.Underline
                ), start = startIndex, end = endIndex
            )
            
        }
        Text(text = annotatedLinkString,
            modifier = Modifier.padding(5.dp))

    }
}
fun randomColor(): Color {
    val red = Random.nextFloat()
    val green = Random.nextFloat()
    val blue = Random.nextFloat()
    return Color(red, green, blue)
}


