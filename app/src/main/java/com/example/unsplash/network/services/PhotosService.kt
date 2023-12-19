package com.example.unsplash.network.services

import com.example.unsplash.modules.photos.model.CategoryModel
import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.modules.photos.model.SearchPhotosResponseModel
import com.example.unsplash.modules.photos.model.TopicModel
import com.example.unsplash.network.NetworkProvider
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface PhotosService {
    @GET("/photos")
    fun getPhotos(): Call<List<PhotoModel>>

    @GET("/collections")
    fun getCollections(): Call<List<CategoryModel>>

    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Call<SearchPhotosResponseModel>

    @GET("/topics")
    fun getTopics(): Call<List<TopicModel>>


    companion object {
        fun newInstance(): PhotosService {
            val networkProvider = NetworkProvider()
            return networkProvider.retrofit.create(PhotosService::class.java)
        }
    }
}