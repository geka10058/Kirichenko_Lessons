package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = requireNotNull(_binding)
    val mainFragment = MainFragment()
    private val passwordCorrect = "password"
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setTitle(R.string.title_activity_login)

        binding.apply {

            btnGoogleSignIn.isEnabled = false

            etEmail.addTextChangedListener {
                email = it.toString()
            }

            tvPassword.addTextChangedListener {
                btnGoogleSignIn.isEnabled = checkPassword(it.toString())/* && email != ""*/
            }

            btnGoogleSignIn.setOnClickListener {
                requireActivity().supportFragmentManager
                goToMainFragment()
            }
        }
    }

    private fun checkPassword(password: String): Boolean {
        return passwordCorrect == password
    }

    private fun goToMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, mainFragment)
            .commit()
    }
}

/*
Extension function to simplify setting an afterTextChanged action to EditText components.
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}*/
