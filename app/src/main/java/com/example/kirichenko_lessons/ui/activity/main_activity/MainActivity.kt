package com.example.kirichenko_lessons.ui.activity.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.databinding.ActivityMainBinding
import com.example.kirichenko_lessons.ui.fragments.HomeFragment
import com.example.kirichenko_lessons.ui.fragments.OfficesFragment
import com.example.kirichenko_lessons.ui.fragments.VacancyFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val vacancyFragment = VacancyFragment()
    private val officesFragment = OfficesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            bottomNavMenu?.setOnNavigationItemSelectedListener {
                handleBottomNavigation(it.itemId)
            }
            bottomNavMenu?.selectedItemId = R.id.fragment_home
        }

    }

    private fun handleBottomNavigation(menuItemId: Int): Boolean = when (menuItemId) {
        R.id.fragment_home -> {
            setCurrentFragment(homeFragment)
            true
        }
        R.id.fragment_vacancy -> {
            setCurrentFragment(vacancyFragment)
            true
        }
        R.id.fragment_offices -> {
            setCurrentFragment(officesFragment)
            true
        }
        else -> false
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .replace(R.id.nav_fragment, fragment)
        .commit()
}