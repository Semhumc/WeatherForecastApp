package com.example.wheatherapp.activity


import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.wheatherapp.R
import com.example.wheatherapp.adapter.ViewPagerAdapter
import com.example.wheatherapp.databinding.ActivityMainBinding
import com.example.wheatherapp.fragments.AnkaraFragment
import com.example.wheatherapp.fragments.IstanbulFragment
import com.example.wheatherapp.fragments.IzmirFragment
import com.example.wheatherapp.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            MainFragment(),
            AnkaraFragment(),
            IstanbulFragment(),
            IzmirFragment()
        )


       //val viewPager2 = findViewById<ViewPager2>(R.id.viewPager)
       //val adapter = ViewPagerAdapter(this, fragments)
       //viewPager2.adapter = adapter

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

       //val window = getWindow()
       //window.setFlags(
       //    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
       //    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
       //)


        //window.setStatusBarColor(Color.TRANSPARENT)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

}
