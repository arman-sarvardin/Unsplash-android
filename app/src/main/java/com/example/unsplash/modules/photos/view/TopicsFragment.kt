package com.example.unsplash.modules.photos.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.unsplash.modules.photos.model.TopicModel
import com.example.unsplash.modules.photos.viewmodel.SearchPhotosViewModel
import com.example.unsplash.modules.photos.viewmodel.TopicsViewModel
import java.time.format.TextStyle

class TopicsFragment: Fragment() {
    val viewModel: TopicsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.didLoad()
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            val topicsListState = viewModel.topics.observeAsState()
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Topics")
                        }
                    )
                }
            ) {
                Column(modifier = Modifier.offset(y = 80.dp)) {
                    topicsListState.value?.let {
                        TopicsGrid(topics = it)
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopicsGrid(topics: List<TopicModel>) {
        LazyHorizontalGrid(
            modifier = Modifier
                .height(340.dp),
            rows = GridCells.Fixed(2),
            content = {
            items(items = topics) {
                Surface(onClick = {

                    childFragmentManager
                        .beginTransaction()
                        .show(TopicPhotosListFragment(it.id))
                        .commit()
                }) {
                    TopicItem(
                            topic = it
                        )
                    }
                }
            }
        )
    }
    @Composable
    fun TopicItem(topic: TopicModel) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box {
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(topic.coverPhoto.urls?.regular)
                            .crossfade(true)
                            .build(),
                    contentDescription = topic.title,
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = topic.title,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance(): TopicsFragment {
            return TopicsFragment()
        }
    }
}