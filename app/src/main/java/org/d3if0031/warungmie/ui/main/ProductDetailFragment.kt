package org.d3if0031.warungmie.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentProductDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)

        viewModel.selectedProduct.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.product = it
                binding.ivProductImage.setImageResource(it.imageId)
                viewModel.clearSelectedProduct()
            }
        }

        binding.fabAddToCart.setOnClickListener { v ->
            binding.product?.let {
                viewModel.addToCart(it)
                Snackbar
                    .make(v, "Product added to cart", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

}