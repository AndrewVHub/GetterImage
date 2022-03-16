package com.example.alefimage.data.remote

import retrofit2.http.GET

interface ImageApi {
    companion object{
        const val BASE_URL = "https://dev-tasks.alef.im/task-m-001/"
    }

    @GET("list.php")
    suspend fun getImageList(): List<String>
}