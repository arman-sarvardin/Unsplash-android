package com.example.unsplash.modules.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.network.services.PhotosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel: ViewModel() {
    private val service: PhotosService = PhotosService.newInstance()
    private val _photos: MutableLiveData<List<PhotoModel>> by lazy {
        MutableLiveData<List<PhotoModel>>()
    }
    val photos: LiveData<List<PhotoModel>> get() = _photos

    fun onCreate() {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        service.getPhotos().enqueue(object: Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                _photos.value = response.body()
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                print(t.message)
            }
        })
    }
}