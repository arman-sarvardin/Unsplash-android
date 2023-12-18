package com.example.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.demo.fragment.SearchPhotosFragment
import com.example.unsplash.databinding.ActivityMainBinding
import com.example.unsplash.modules.photos.view.PhotosFragment
import com.example.unsplash.modules.photos.view.TopicsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var initialFragment: Fragment = PhotosFragment.newInstance()
    private val photoListFragment = PhotosFragment()
    private val searchFragment: SearchPhotosFragment = SearchPhotosFragment()
    private val topicsFragment: TopicsFragment = TopicsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, topicsFragment)
            .add(R.id.fragment_container_view, photoListFragment)
            .add(R.id.fragment_container_view, searchFragment)
            .hide(searchFragment)
            .hide(topicsFragment)
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.photo_list -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(initialFragment)
                        .show(photoListFragment)
                        .commit()
                    initialFragment = photoListFragment
                    true
                }
                R.id.photo_search -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(initialFragment)
                        .show(searchFragment)
                        .commit()
                    initialFragment = searchFragment
                    true
                }

                R.id.topics_fragment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(initialFragment)
                        .show(topicsFragment)
                        .commit()
                    initialFragment = topicsFragment
                    true
                }

                else -> {false}
            }
        }
    }
}