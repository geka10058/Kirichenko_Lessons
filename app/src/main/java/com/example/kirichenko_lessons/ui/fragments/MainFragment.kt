package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val homeFragment = HomeFragment()
    private val vacancyFragment = VacancyFragment()
    private val officesFragment = OfficesFragment()
    private var currentMenuItemId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            bottomNavMenu.setOnNavigationItemSelectedListener {
                handleBottomNavigation(it.itemId)
            }
            bottomNavMenu.selectedItemId = R.id.fragment_home
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    when (currentMenuItemId) {
                        R.id.fragment_vacancy -> {
                            handleBottomNavigation(R.id.fragment_home)
                            isEnabled = true
                        }
                        R.id.fragment_offices -> {
                            handleBottomNavigation(R.id.fragment_home)
                            isEnabled = true
                        }
                        else -> {
                            isEnabled = false
                            requireActivity().onBackPressed()
                        }
                    }
                }
            })
    }

    private fun handleBottomNavigation(menuItemId: Int): Boolean = when (menuItemId) {
        R.id.fragment_home -> {
            setCurrentFragment(homeFragment, R.string.title_home, menuItemId)
            true
        }
        R.id.fragment_vacancy -> {
            setCurrentFragment(vacancyFragment, R.string.vacancy, menuItemId)
            true
        }
        R.id.fragment_offices -> {
            setCurrentFragment(officesFragment, R.string.title_offices, menuItemId)
            true
        }
        else -> false
    }

    private fun setCurrentFragment(fragment: Fragment, fragmentTitle: Int, menuItemId: Int) {
        currentMenuItemId = menuItemId
        childFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
        requireActivity().setTitle(fragmentTitle)
    }
}