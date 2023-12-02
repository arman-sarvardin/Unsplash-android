package com.example.unsplash.modules.photos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unsplash.R
import com.example.unsplash.databinding.PhotoItemViewBinding
import com.example.unsplash.databinding.PhotosFragmentBinding
import com.example.unsplash.modules.photos.model.PhotoModel
import com.example.unsplash.modules.photos.model.PhotoModelDiffUtil

class PhotoListAdapter: ListAdapter<PhotoModel, PhotoListAdapter.ViewHolder>(PhotoModelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PhotoItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: PhotoItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(model: PhotoModel) {
            Glide
                .with(context)
                .load(model.urls.regular)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.photoImage)


            if(model.description != null) {
                binding.photoDescription.text = model.description
                binding.photoDescription.background = ContextCompat.getDrawable(context, R.drawable.dim_background)
            }
        }
    }
}