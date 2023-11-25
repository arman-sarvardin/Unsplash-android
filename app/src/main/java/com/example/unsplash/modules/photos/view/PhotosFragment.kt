package com.example.unsplash.modules.photos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.PhotosFragmentBinding
import com.example.unsplash.modules.photos.view.adapter.PhotoListAdapter
import com.example.unsplash.modules.photos.viewmodel.PhotosViewModel

class PhotosFragment: Fragment() {
    private lateinit var binding: PhotosFragmentBinding
    private val viewModel: PhotosViewModel by viewModels()
    private var photoListAdapter: PhotoListAdapter? = null


    companion object {
        fun newInstance(): PhotosFragment = PhotosFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PhotosFragmentBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoListAdapter = PhotoListAdapter()
        binding.photoList.adapter = photoListAdapter

        viewModel.onCreate()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.photos.observe(viewLifecycleOwner) {
            photoListAdapter?.submitList(it)
        }
    }

    private fun setupPagination() {

    }
}