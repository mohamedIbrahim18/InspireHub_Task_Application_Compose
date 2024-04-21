package com.example.inspirehub_task.viewmodel

import com.godaddy.android.colorpicker.HsvColor

data class Ui(
    val error:String="",
    val loading:Boolean=true,
    val stories:List<String> = emptyList(),
    val headlines:List<String> = emptyList(),
    val fontSize:Int=13,
    val backgroundColor: HsvColor = HsvColor(0.0f, 0.0f, 1.0f, 1.0f),
    val textColor: HsvColor = HsvColor(0.0f, 0.0f, 0.0f, 1.0f),
    val ShowDialogForBackground:Boolean=false,
    val ShowDialogForText:Boolean=false,

    )