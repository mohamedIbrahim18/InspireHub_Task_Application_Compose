package com.example.inspirehub_task.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspirehub_task.util.Response
import com.example.inspirehub_task.repository.StoryRepository
import com.godaddy.android.colorpicker.HsvColor
import kotlinx.coroutines.launch
import java.io.InputStream

class StoryViewModel(private val repo: StoryRepository) : ViewModel() {
    private val _uiState = mutableStateOf(Ui())
    val uiState = _uiState

    fun getStoriesData(inputStream: InputStream) = viewModelScope.launch {
        repo.parseTextFile(inputStream).collect { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        _uiState.value = _uiState.value.copy(stories = it.second, headlines = it.first)
                    }
                    _uiState.value = _uiState.value.copy(loading = false)
                }
                is Response.Error -> {
                    _uiState.value = _uiState.value.copy(loading = false, error = response.message ?: "Unknown Error Occurred")
                }
                is Response.Loading -> {
                    _uiState.value = _uiState.value.copy(loading = true)
                }
            }
        }
    }

    fun updateUiState(
        fontSize: Int? = null,
        backgroundColor: HsvColor? = null,
        textColor: HsvColor? = null,
        showBackGroundDialog: Boolean? = null,
        showTextDialog: Boolean? = null,
        error: String? = null
    ) {
        _uiState.value = _uiState.value.copy(
            fontSize = fontSize ?: _uiState.value.fontSize,
            backgroundColor = backgroundColor ?: _uiState.value.backgroundColor,
            textColor = textColor ?: _uiState.value.textColor,
            ShowDialogForBackground = showBackGroundDialog ?: _uiState.value.ShowDialogForBackground,
            ShowDialogForText = showTextDialog ?: _uiState.value.ShowDialogForText,
            error = error ?: _uiState.value.error
        )
    }
}
