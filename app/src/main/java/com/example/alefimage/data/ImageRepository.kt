package com.example.alefimage.data

import com.example.alefimage.data.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadImageList(): List<String>{
        return remoteDataSource.getImageList()
    }
}