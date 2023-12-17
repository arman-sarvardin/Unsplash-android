package com.example.unsplash.modules.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplash.modules.photos.model.CategoryModel
import com.example.unsplash.network.services.PhotosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPhotosViewModel: ViewModel() {
    val photosService: PhotosService = PhotosService.newInstance()
    private val _categories: MutableLiveData<List<CategoryModel>> by lazy {
        MutableLiveData<List<CategoryModel>>()
    }
    val categories: LiveData<List<CategoryModel>> get() = _categories

    fun fetchCategories() {
        photosService.getCollections()
            .enqueue(object : Callback<List<CategoryModel>> {
                override fun onResponse(
                    call: Call<List<CategoryModel>>,
                    response: Response<List<CategoryModel>>
                ) {
                    if (response.body() != null) {
                        _categories.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<CategoryModel>>, t: Throwable) {

                }
            })
    }
}