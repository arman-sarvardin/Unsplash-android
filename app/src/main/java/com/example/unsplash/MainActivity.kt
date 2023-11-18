package com.example.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unsplash.databinding.ActivityMainBinding
import com.example.unsplash.modules.photos.view.PhotosFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var initialFragment: PhotosFragment = PhotosFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, initialFragment)
            .commit()
    }
}