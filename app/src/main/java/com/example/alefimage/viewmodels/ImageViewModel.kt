package com.example.alefimage.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alefimage.data.ImageRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ImageViewModel(
    private val repositoryImage: ImageRepository
): ViewModel() {
    private val _ld = MutableLiveData<List<String>>()
    val ld: LiveData<List<String>> = _ld

    private val _action = MutableLiveData<ImageAction>()
    val action: LiveData<ImageAction> = _action


    init {
        getImages()
    }

    fun getImages() {
        viewModelScope.launch {
            //На тот случай если сервер умер (тк ссылки некоторые не рабочие. подстраховОчка)
            try {
                _ld.value = repositoryImage.loadImageList()
            }catch (e: Throwable){
                _action.value = ImageAction.ShowError("Something went wrong")
            }
            _action.value = ImageAction.HideLoader
        }
    }


    sealed class ImageAction {
        object HideLoader : ImageAction()
        data class ShowError(val errorMessage: String): ImageAction()
    }
}