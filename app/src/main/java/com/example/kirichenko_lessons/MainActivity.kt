package com.example.kirichenko_lessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.kirichenko_lessons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnContacts.setOnClickListener {
                hideGroupEndShowProgressBar()
            }

            btnGallery.setOnClickListener {
                hideGroupEndShowProgressBar()
            }

            btnVacancy.setOnClickListener {
                hideGroupEndShowProgressBar()
            }

            ivLogo.setOnClickListener {
                showGroupEndHideProgressBar()
            }
        }
    }

    private fun hideGroupEndShowProgressBar(){
        binding.group.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showGroupEndHideProgressBar(){
        binding.group.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }
}