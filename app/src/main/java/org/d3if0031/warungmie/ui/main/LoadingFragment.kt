package org.d3if0031.warungmie.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment(R.layout.fragment_loading) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentLoadingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoadingBinding.bind(view)

        binding.root.setOnClickListener {
            viewModel.clearCart()
            Snackbar.make(it, "Order Successful", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_loadingFragment_to_homeFragment)
        }
    }

}