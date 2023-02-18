package com.example.kirichenko_lessons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kirichenko_lessons.databinding.ActivityLinearBinding

class LinearActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            Toast.makeText(applicationContext,R.string.linear_layout, Toast.LENGTH_SHORT).show()

            ivFirstActivity.setOnClickListener {
                val intent = Intent(this@LinearActivity, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            ivThirdActivity.setOnClickListener {
                val intent = Intent(this@LinearActivity, GridActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}