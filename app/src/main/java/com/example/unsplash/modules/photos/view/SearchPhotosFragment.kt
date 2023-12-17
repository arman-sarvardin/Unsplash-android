package com.example.demo.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unsplash.modules.photos.viewmodel.SearchPhotosViewModel

class SearchPhotosFragment: Fragment() {
    val viewModel: SearchPhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val resultPhotos = viewModel.resultPhotos.observeAsState()

            val listState = rememberLazyListState()

            Column(modifier = Modifier.fillMaxWidth()) {
                SearchBar(onValueChange = { viewModel.search(text = it) } )
                LazyColumn(state = listState) {
                    resultPhotos.value?.let { photoModel ->
                        items(photoModel) {
                            AsyncImage(modifier = Modifier.padding(vertical = 8.dp),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.urls.regular)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = it.description
                            )
                        }
                    }
                }
            }

            LaunchedEffect(listState) {
                listState.layoutInfo.visibleItemsInfo.let { visibleItems ->
                    val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: 0
                    val totalItemCount = listState.layoutInfo.totalItemsCount
                    val buffer = 10

                    if (totalItemCount - lastVisibleItemIndex <= buffer) {
                        viewModel.addPage()
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCategories()
        viewModel.didLoad()
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar(onValueChange: (String) -> Unit) {
        var text by remember {
            mutableStateOf("")
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it; onValueChange(it) },
            label = { Text("Search") },
        )
    }
}