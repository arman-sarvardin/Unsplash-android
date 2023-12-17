package com.example.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.unsplash.databinding.FragmentSearchPhotosBinding
import com.example.unsplash.modules.photos.view.adapter.CategoryListAdapter
import com.example.unsplash.modules.photos.viewmodel.SearchPhotosViewModel

class SearchPhotosFragment: Fragment() {
    val viewModel: SearchPhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Text("text")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCategories()
    }
}