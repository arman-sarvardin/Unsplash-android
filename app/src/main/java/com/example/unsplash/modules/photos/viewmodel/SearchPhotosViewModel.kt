package com.example.unsplash.modules.photos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unsplash.modules.photos.di.moduleInject
import com.example.unsplash.modules.photos.model.CategoryModel
import com.example.unsplash.network.services.PhotosService
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPhotosViewModel: ViewModel() {
    val photosService: PhotosService = PhotosService.newInstance()

    fun fetchCategories() {
        photosService.getCollections()
            .enqueue(object : Callback<List<CategoryModel>> {
                override fun onResponse(
                    call: Call<List<CategoryModel>>,
                    response: Response<List<CategoryModel>>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<List<CategoryModel>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}