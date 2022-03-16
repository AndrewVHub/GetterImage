package com.example.alefimage.data.remote

interface RemoteDataSource {
    suspend fun getImageList(): List<String>
}