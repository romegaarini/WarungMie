package org.d3if0031.warungmie.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        val adapter = CartAdapter(viewModel)
        binding.rvCart.adapter = adapter

        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.submitList(it)

            binding.btnCheckOut.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.selectedProduct.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_cartFragment_to_productDetailFragment)
            }
        }

        binding.btnCheckOut.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_checkOutFragment)
        }
    }
}