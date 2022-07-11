package org.d3if0031.warungmie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.d3if0031.warungmie.data.Product
import org.d3if0031.warungmie.databinding.ViewProductMenuBinding

class MenuAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Product, MenuAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewProductMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewProductMenuBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }

        fun bind(product: Product, viewModel: MainViewModel) = binding.apply {
            this.product = product
            this.ivProductImage.setImageResource(product.imageId)

            this.root.setOnClickListener {
                viewModel.setSelectedProduct(product)
            }

            this.ivAddToCart.setOnClickListener {
                viewModel.addToCart(product)
                Snackbar
                    .make(it, "Product added to cart", Snackbar.LENGTH_SHORT)
                    .show()
            }

            executePendingBindings()
        }
    }

}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
