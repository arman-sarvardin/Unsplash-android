package com.example.unsplash.modules.photos.viewmodel

import android.adservices.topics.Topic
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplash.modules.photos.model.TopicModel
import com.example.unsplash.network.services.PhotosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicsViewModel: ViewModel() {
    val photosService: PhotosService = PhotosService.newInstance()
    val _topics: MutableLiveData<List<TopicModel>> by lazy {
        MutableLiveData<List<TopicModel>>()
    }
    val topics: LiveData<List<TopicModel>> get() = _topics
    fun didLoad() {
        photosService.getTopics()
            .enqueue(object: Callback<List<TopicModel>> {
                override fun onResponse(
                    call: Call<List<TopicModel>>,
                    response: Response<List<TopicModel>>
                ) {
                    _topics.value = response.body()
                }
                override fun onFailure(call: Call<List<TopicModel>>, t: Throwable) {

                }
            })
    }
}