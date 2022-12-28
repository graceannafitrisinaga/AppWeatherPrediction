package com.graceannafitrisinaga.weatherprediction.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.graceannafitrisinaga.weatherprediction.R
import com.graceannafitrisinaga.weatherprediction.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    //menggunakan data binding untuk menampilkan activity_main
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //menjalankan navigasi dengan controller
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
}