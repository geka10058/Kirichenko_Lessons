package com.example.kirichenko_lessons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kirichenko_lessons.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            Toast.makeText(applicationContext,R.string.grid_layout, Toast.LENGTH_SHORT).show()

            ivFirstActivity.setOnClickListener {
                val intent = Intent(this@GridActivity, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            ivSecondActivity.setOnClickListener {
                val intent = Intent(this@GridActivity, LinearActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}