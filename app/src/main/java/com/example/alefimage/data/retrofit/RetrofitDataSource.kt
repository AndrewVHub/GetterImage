package com.example.alefimage.data.retrofit

import com.example.alefimage.data.remote.ImageApi
import com.example.alefimage.data.remote.RemoteDataSource

class RetrofitDataSource(private val imageApi: ImageApi): RemoteDataSource {
    override suspend fun getImageList(): List<String> = imageApi.getImageList()
}