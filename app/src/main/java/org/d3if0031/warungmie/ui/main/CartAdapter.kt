package org.d3if0031.warungmie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0031.warungmie.data.Cart
import org.d3if0031.warungmie.databinding.ViewProductCartBinding

class CartAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Cart, CartAdapter.ViewHolder>(CartDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = getItem(position)
        holder.bind(cart, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewProductCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewProductCartBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }

        fun bind(cart: Cart, viewModel: MainViewModel) = binding.apply {
            this.cart = cart
            this.ivProductImage.setImageResource(cart.product.imageId)

            this.root.setOnClickListener {
                viewModel.setSelectedProduct(cart.product)
            }

            this.ivAddQuantity.setOnClickListener {
                this.cart = cart
                viewModel.updateQuantity(cart.product.id, cart.quantity + 1)
            }

            this.ivSubtractQuantity.setOnClickListener {
                if (cart.quantity - 1 == 0) {
                    viewModel.removeCartProduct(cart.product.id)
                } else {
                    viewModel.updateQuantity(cart.product.id, cart.quantity - 1)
                }

                this.cart = cart
            }

            executePendingBindings()
        }
    }

}

class CartDiffCallback : DiffUtil.ItemCallback<Cart>() {
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem == newItem
    }
}
