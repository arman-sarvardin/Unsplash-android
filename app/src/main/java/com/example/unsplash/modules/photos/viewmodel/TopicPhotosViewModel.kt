package com.example.unsplash.modules.photos.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.network.services.PhotosService

class TopicPhotosViewModel(val topicId: String): ViewModel() {
    val photosService: PhotosService = PhotosService.newInstance()

    val _photos: MutableLiveData<List<PhotoModel>> by lazy {
        MutableLiveData<List<PhotoModel>>()
    }
    val photos: LiveData<List<PhotoModel>> get() = _photos
    fun didLoad() {

    }

    inner class Factory(
        private val topicId: String
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopicPhotosViewModel(topicId) as T
        }
    }
}