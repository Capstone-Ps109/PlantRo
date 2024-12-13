package com.example.plantro.ui.HomeActivity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.ActivityHomeBinding
import com.example.plantro.ViewModelFactory
import com.example.plantro.ui.login.LoginActivity
import kotlinx.coroutines.delay

import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeActivityViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var token = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding layout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSession().observe(this) { user ->
            runBlocking { delay(3500) }
            Log.d(TAG, "Token yang diterima: ${user.token}")
            if (user.token.isNotEmpty()) {
                token = user.token
            } else {
                navigateToLogin() // Arahkan ke LoginActivity jika token kosong
            }
        }

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        // Konfigurasi navigasi dan bottom navigation
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_input,
                R.id.navigation_history,
                R.id.navigation_profile
            )
        )

        // Hubungkan BottomNavigationView dengan NavController
        navView.setupWithNavController(navController)
    }
    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
