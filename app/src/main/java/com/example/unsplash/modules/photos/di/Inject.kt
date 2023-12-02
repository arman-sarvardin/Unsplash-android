package com.example.unsplash.modules.photos.di

import com.example.unsplash.modules.photos.viewmodel.SearchPhotosViewModel
import com.example.unsplash.network.services.PhotosService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

public val moduleInject = module {
    viewModel {
        SearchPhotosViewModel()
    }

    single {
        PhotosService.newInstance()
    }
}