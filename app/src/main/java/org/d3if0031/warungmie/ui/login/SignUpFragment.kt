package org.d3if0031.warungmie.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        val inputUsername = binding.tietUsername
        val inputEmail = binding.tietEmail
        val inputPassword = binding.tietPassword

        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it.error.isNotEmpty()) {
                Snackbar.make(binding.root, it.error, Snackbar.LENGTH_LONG)
                    .show()
                viewModel.resetState()
            }

            if (it.isLoginSuccess) {
                Toast.makeText(requireContext(), "Account created, let's login", Toast.LENGTH_SHORT)
                    .show()
//                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                findNavController().navigateUp()
                viewModel.resetState()
            }
        }

        binding.btnSignUp.setOnClickListener {
            var isValid = true

            val username = inputUsername.text.toString()
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (username.isEmpty()) {
                inputUsername.error = "Username can't be empty"
                isValid = false
            }

            if (email.isEmpty()) {
                inputEmail.error = "Email can't be empty"
                isValid = false
            }

            if (password.isEmpty()) {
                inputPassword.error = "Password can't be empty"
                isValid = false
            }

            if (!isValid) {
                return@setOnClickListener
            }

            viewModel.register(username, email, password)
        }
    }

}