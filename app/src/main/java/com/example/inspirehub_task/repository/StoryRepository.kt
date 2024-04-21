package com.example.inspirehub_task.repository

import com.example.inspirehub_task.util.Response
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface StoryRepository {
    fun parseTextFile(inputStream: InputStream): Flow<Response<Pair<MutableList<String>, MutableList<String>>>>
}