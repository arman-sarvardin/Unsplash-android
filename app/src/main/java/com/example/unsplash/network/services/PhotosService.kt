package com.example.unsplash.network.services

import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.network.NetworkProvider
import retrofit2.http.GET
import retrofit2.Call

interface PhotosService {
    @GET("/photos")
    fun getPhotos(): Call<List<PhotoModel>>

    companion object {
        fun newInstance():PhotosService {
            val networkProvider = NetworkProvider()
            val service = networkProvider.retrofit.create(PhotosService::class.java)
            return service
        }
    }
}