package com.example.kirichenko_lessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kirichenko_lessons.databinding.ActivityMainBinding
import com.example.kirichenko_lessons.ui.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val loginFragment = LoginFragment()
    /*private val homeFragment = HomeFragment()
    private val vacancyFragment = VacancyFragment()
    private val officesFragment = OfficesFragment()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.apply {

            /*bottomNavMenu?.setOnNavigationItemSelectedListener {
                handleBottomNavigation(it.itemId)
            }*/
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main, loginFragment)
                .commit()
            //bottomNavMenu?.selectedItemId = R.id.fragment_home
        }

    }

    /*private fun handleBottomNavigation(menuItemId: Int): Boolean = when (menuItemId) {
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
    }*/

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .replace(R.id.container_main, fragment)
        .commit()
}