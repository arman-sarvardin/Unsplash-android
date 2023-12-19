package com.example.unsplash.modules.photos.view

import android.adservices.topics.Topic
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unsplash.modules.photos.viewmodel.TopicPhotosViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TopicPhotosListFragment(val topicId: String): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            val viewModel: TopicPhotosViewModel = TopicPhotosViewModel(topicId = topicId)
            val photoListState = viewModel.photos.observeAsState()
            LazyColumn(content = {
                photoListState.value?.let {
                    items(it) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.urls.regular)
                                .crossfade(true)
                                .build()
                            ,
                            contentDescription = it.description
                        )
                    }
                }
            })
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}