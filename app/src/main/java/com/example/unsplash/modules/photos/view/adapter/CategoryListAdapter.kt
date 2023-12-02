package com.example.unsplash.modules.photos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplash.databinding.PhotoCategoryViewBinding
import com.example.unsplash.modules.photos.model.CategoryModel
import androidx.recyclerview.widget.ListAdapter



class CategoryListAdapter: ListAdapter<CategoryModel, CategoryListAdapter.ViewHolder>(CategoryModelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PhotoCategoryViewBinding.inflate(
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
        private val binding: PhotoCategoryViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryModel: CategoryModel) {
            Glide.with(binding.categoryImage)
                .load(categoryModel.urls.regular)
            binding.categoryTitle.text = categoryModel.title
        }
    }

    class CategoryModelDiffUtil: DiffUtil.ItemCallback<CategoryModel>() {
        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }
    }
}