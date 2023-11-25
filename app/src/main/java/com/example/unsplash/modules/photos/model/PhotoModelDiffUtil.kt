package com.example.unsplash.modules.photos.model

import androidx.recyclerview.widget.DiffUtil

class PhotoModelDiffUtil: DiffUtil.ItemCallback<PhotoModel>() {

    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem == newItem
    }
}