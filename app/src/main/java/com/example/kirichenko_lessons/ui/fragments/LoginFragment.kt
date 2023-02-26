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

            tvEmail.addTextChangedListener {
                email = it.toString()
            }

            tvPassword.addTextChangedListener {
                btnGoogleSignIn.isEnabled = checkPassword(it.toString())
            }

            btnGoogleSignIn.setOnClickListener {
                requireActivity().supportFragmentManager
                goToMainFragment()
            }

        }

        /*val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }*/
    }

    /* private fun updateUiWithUser(model: LoggedInUserView) {
         val welcome = getString(R.string.welcome)
         val displayName = model.displayName
         // TODO : initiate successful logged in experience
         Toast.makeText(
             applicationContext,
             "$welcome $displayName",
             Toast.LENGTH_LONG
         ).show()
     }

     private fun showLoginFailed(@StringRes errorString: Int) {
         Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
     }*/

    private fun checkPassword(password:String):Boolean{
        return passwordCorrect == password
    }

    private fun goToMainFragment(){
        /*val intent = Intent(this@LoginActivity, MainActivity()::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)*/

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
