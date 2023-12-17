package com.example.unsplash.modules.photos.model

import com.google.gson.annotations.SerializedName

data class SearchPhotosResponseModel(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<PhotoModel>
)