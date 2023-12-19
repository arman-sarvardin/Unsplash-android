package com.example.unsplash.modules.photos.model

import com.google.gson.annotations.SerializedName

data class TopicModel(
    val title: String,
    val id: String,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto
)