package com.example.inspirehub_task.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.inspirehub_task.R
import com.example.inspirehub_task.repository.StoryRepositoryImpl
import com.example.inspirehub_task.ui.theme.InspireHub_TaskApplicationTheme
import com.example.inspirehub_task.viewmodel.StoryViewModel
import com.example.inspirehub_task.viewmodel.StoryViewModelFactory


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = StoryRepositoryImpl() // Instantiate your repository here
        val viewModelFactory = StoryViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(StoryViewModel::class.java)

        setContent {
            InspireHub_TaskApplicationTheme {
                LaunchedEffect(true) {
                    viewModel.getStoriesData(resources.openRawResource(R.raw.data))
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = viewModel.uiState.value.backgroundColor.toColor()
                ) {

                    if( viewModel.uiState.value.ShowDialogForBackground||viewModel.uiState.value.ShowDialogForText)
                        ColorDialog()

                    if(viewModel.uiState.value.loading){

                        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    if(viewModel.uiState.value.error!=""){
                        Toast.makeText(this,viewModel.uiState.value.error,Toast.LENGTH_SHORT).show()
                        viewModel.updateUiState(error = "")
                    }
                        Box(contentAlignment = Alignment.BottomCenter) {
                            MainPage(Modifier.wrapContentHeight())
                            Row()
                        }


                }
            }
        }
    }
}


