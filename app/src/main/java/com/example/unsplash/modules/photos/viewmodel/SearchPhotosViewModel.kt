package com.example.unsplash.modules.photos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplash.modules.photos.model.CategoryModel
import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.modules.photos.model.SearchPhotosResponseModel
import com.example.unsplash.network.services.PhotosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPhotosViewModel: ViewModel() {
    val photosService: PhotosService = PhotosService.newInstance()
    private val _resultPhotos: MutableLiveData<List<PhotoModel>> by lazy {
        MutableLiveData<List<PhotoModel>>()
    }
    private val _categories: MutableLiveData<List<CategoryModel>> by lazy {
        MutableLiveData<List<CategoryModel>>()
    }
    val resultPhotos: LiveData<List<PhotoModel>> get() = _resultPhotos
    var photos: List<PhotoModel> = listOf()
    var currentPage: Int = 1
    var currentText: String = "Istanbul"

    fun didLoad() {
        addPage()
    }

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

    fun addPage() {
        photosService.searchPhotos(query = currentText, page = currentPage)
            .enqueue(object : Callback<SearchPhotosResponseModel> {
                override fun onFailure(call: Call<SearchPhotosResponseModel>, t: Throwable) {
                    print("failure")
                }

                override fun onResponse(
                    call: Call<SearchPhotosResponseModel>,
                    response: Response<SearchPhotosResponseModel>
                ) {
                    val newPhotos = response.body()?.results
                    val currentPhotos = _resultPhotos.value ?: listOf()

                    // Combine the current list with the new list
                    val updatedList = currentPhotos + (newPhotos ?: listOf())

                    // Update the LiveData with the new list
                    _resultPhotos.value = updatedList
                    currentPage++
                }
            })
    }

    fun search(text: String) {
        _resultPhotos.value = listOf()
        currentPage = 1
        currentText = text
        photosService.searchPhotos(query = currentText, page = currentPage + 1)
            .enqueue(object : Callback<SearchPhotosResponseModel> {
                override fun onFailure(call: Call<SearchPhotosResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SearchPhotosResponseModel>,
                    response: Response<SearchPhotosResponseModel>
                ) {
                    val newPhotos = response.body()?.results
                    val currentPhotos = _resultPhotos.value ?: listOf()

                    // Combine the current list with the new list
                    val updatedList = currentPhotos + (newPhotos ?: listOf())

                    // Update the LiveData with the new list
                    _resultPhotos.value = updatedList
                    currentPage++
                }
            })
    }
}