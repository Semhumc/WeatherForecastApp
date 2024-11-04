package com.example.wheatherapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.wheatherapp.R
import com.example.wheatherapp.adapter.ViewPagerAdapter
import com.example.wheatherapp.fragments.AnkaraFragment
import com.example.wheatherapp.fragments.IstanbulFragment
import com.example.wheatherapp.fragments.IzmirFragment
import com.example.wheatherapp.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = listOf(
            MainFragment(),
            AnkaraFragment(),
            IstanbulFragment(),
            IzmirFragment()
        )

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }
}
