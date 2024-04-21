package com.example.inspirehub_task.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inspirehub_task.viewmodel.StoryViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun MainPage(modifier: Modifier, viewModel: StoryViewModel = viewModel()) {

    val listOfStates = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listOfStates,
        horizontalAlignment = Alignment.End,
        modifier = modifier.padding(bottom = 24.dp) // Adding padding only to the bottom
    ) {

        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "اجمل القصص القصيرة",
                fontSize = (viewModel.uiState.value.fontSize + 8).sp,
                style = TextStyle(color = viewModel.uiState.value.textColor.toColor())
            )
        }

        item {
            Link()
        }

        items(viewModel.uiState.value.headlines.size) {
            Text(
                viewModel.uiState.value.headlines[it],
                fontSize = viewModel.uiState.value.fontSize.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        coroutineScope.launch {
                            listOfStates.animateScrollToItem(index = (it * 1 + viewModel.uiState.value.headlines.size+2))
                        }
                    },
                style = TextStyle(color = Color.Blue)
            )
        }

        items(viewModel.uiState.value.stories.size) {
            StoryItem(title = (viewModel.uiState.value.headlines[it]), content = viewModel.uiState.value.stories[it], index = it)
        }
    }
}
