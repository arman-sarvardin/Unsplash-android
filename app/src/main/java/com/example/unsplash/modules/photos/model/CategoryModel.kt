package com.example.unsplash.modules.photos.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    val title: String,
    val description: String,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto?
)

data class CoverPhoto(
    val urls: PhotoURLsDto?
)