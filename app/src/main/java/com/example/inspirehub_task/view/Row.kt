package com.example.inspirehub_task.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inspirehub_task.R
import com.example.inspirehub_task.viewmodel.StoryViewModel

@Composable
fun Row(viewModel: StoryViewModel = viewModel()){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Blue)
        .padding(2.dp),
        horizontalArrangement= Arrangement.SpaceEvenly
    ){
        Icon(
            painter = painterResource(R.drawable.ic_background_color),
            contentDescription = "",
            modifier = Modifier.clickable {
                viewModel.updateUiState(showBackGroundDialog=true)
            }
        )
        Icon(
            painter = painterResource(R.drawable.ic_change_text_color),
            contentDescription = "",
            modifier = Modifier.clickable {
                viewModel.updateUiState(showTextDialog=true)
            }
        )
        Icon(
            painter = painterResource(R.drawable.ic_zoomin),
            contentDescription = "",
            modifier = Modifier.clickable {
                if (viewModel.uiState.value.fontSize >20){
                    return@clickable
                }
                viewModel.updateUiState(fontSize =viewModel.uiState.value.fontSize+1 )

            }
        )
        Icon(
            painter = painterResource(R.drawable.ic_zoomout),
            contentDescription = "",
            modifier = Modifier.clickable {
                if (viewModel.uiState.value.fontSize <13){
                    return@clickable
                }
                viewModel.updateUiState(fontSize =viewModel.uiState.value.fontSize-1 )
            }
        )
    }
}