package com.example.inspirehub_task.repository

import com.example.inspirehub_task.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStream

class StoryRepositoryImpl() : StoryRepository {
    override fun parseTextFile(inputStream: InputStream)
            : Flow<Response<Pair<MutableList<String>, MutableList<String>>>> = flow {
        emit(Response.Loading())
        try {
            val lines = inputStream.bufferedReader(Charsets.UTF_8).readLines()
            val titles = mutableListOf<String>()
            val content = mutableListOf<String>()

            for (i in lines.indices){
                if (i%2==0){
                    titles.add(lines[i])
                } else {
                    content.add(lines[i])
                }
            }
            emit(Response.Success(Pair(titles, content)))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Unexpected Error Occurred"))
        }
    }
}