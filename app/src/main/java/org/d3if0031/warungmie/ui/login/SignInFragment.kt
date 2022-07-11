package org.d3if0031.warungmie.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        val inputUsername = binding.tietUsername
        val inputPassword = binding.tietPassword

        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it.error.isNotEmpty()) {
                Snackbar.make(binding.root, it.error, Snackbar.LENGTH_LONG)
                    .show()
                viewModel.resetState()
            }

            if (it.isLoginSuccess) {
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                viewModel.resetState()
            }
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            val username = inputUsername.text.toString()
            val password = inputPassword.text.toString()

            if (username.isEmpty()) {
                inputUsername.error = "Username can't be empty"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                inputPassword.error = "Password can't be empty"
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }
    }

}