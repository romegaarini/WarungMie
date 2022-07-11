package org.d3if0031.warungmie.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        val adapter = MenuAdapter(viewModel)

        binding.rvMenu.adapter = adapter
        adapter.submitList(viewModel.products)

        viewModel.selectedProduct.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_menuFragment_to_productDetailFragment)
            }
        }
    }

}