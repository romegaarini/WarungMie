package org.d3if0031.warungmie.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentCheckOutBinding

class CheckOutFragment : Fragment(R.layout.fragment_check_out) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentCheckOutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckOutBinding.bind(view)

        val adapter = CheckOutAdapter()
        binding.rvCheckOutProduct.adapter = adapter

        viewModel.cart.observe(viewLifecycleOwner) { carts ->
            var total = 0F
            carts.map { cart ->
                total += (cart.product.price * cart.quantity)
            }

            binding.tvSubtotal.text = "Rp. $total"
            binding.tvTotal.text = "Rp. ${total + 5000F}"
            adapter.submitList(carts)
        }

        binding.btnOrder.setOnClickListener {
            findNavController().navigate(R.id.action_checkOutFragment_to_loadingFragment)
        }
    }

}