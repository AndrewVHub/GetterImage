package com.example.alefimage.di

import com.example.alefimage.data.ImageRepository
import com.example.alefimage.data.remote.ImageApi
import com.example.alefimage.data.remote.RemoteDataSource
import com.example.alefimage.data.retrofit.RetrofitDataSource
import com.example.alefimage.viewmodels.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ImageApi> {
        Retrofit.Builder()
            .baseUrl(ImageApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ImageApi::class.java)
    }

    single<RemoteDataSource> { RetrofitDataSource(get()) }
    single { ImageRepository(get()) }

}

val viewmodelModule = module {
    viewModel { ImageViewModel(get()) }
}