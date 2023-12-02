package com.example.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplash.databinding.FragmentSearchPhotosBinding
import com.example.unsplash.modules.photos.view.adapter.CategoryListAdapter
import com.example.unsplash.modules.photos.viewmodel.SearchPhotosViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchPhotosFragment: Fragment() {
    val viewModel: SearchPhotosViewModel by sharedViewModel()
    private lateinit var binding: FragmentSearchPhotosBinding
    private var categoryListAdapter: CategoryListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPhotosBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryListAdapter = CategoryListAdapter()
        super.onViewCreated(view, savedInstanceState)
    }
}