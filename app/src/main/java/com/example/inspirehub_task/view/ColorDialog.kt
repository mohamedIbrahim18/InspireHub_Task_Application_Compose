package com.example.inspirehub_task.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inspirehub_task.viewmodel.StoryViewModel
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.harmony.ColorHarmonyMode
import com.godaddy.android.colorpicker.harmony.HarmonyColorPicker

@Composable
fun ColorDialog(viewModel: StoryViewModel = viewModel()){
    var finalColor: HsvColor =
        if(viewModel.uiState.value.ShowDialogForText)
            viewModel.uiState.value.textColor
        else
            viewModel.uiState.value.backgroundColor

    Dialog(onDismissRequest = {
        viewModel.updateUiState(showTextDialog=false ,showBackGroundDialog=false)
    }) {
        Column (horizontalAlignment=Alignment.CenterHorizontally){
            HarmonyColorPicker(
                color = finalColor.toColor(),
                harmonyMode = ColorHarmonyMode.NONE,
                modifier = Modifier.size(400.dp),
                onColorChanged = { color ->
                    finalColor=color
                })
            Button(modifier = Modifier.padding(all=8.dp), onClick = {
                finalColor.let{
                    if(viewModel.uiState.value.ShowDialogForText)
                        viewModel.updateUiState(textColor = it,showTextDialog=false ,showBackGroundDialog=false)
                    else
                        viewModel.updateUiState(backgroundColor = it,showTextDialog=false ,showBackGroundDialog=false)
                }
            }) {
                Text("choose color")
            }
        }
    }
}