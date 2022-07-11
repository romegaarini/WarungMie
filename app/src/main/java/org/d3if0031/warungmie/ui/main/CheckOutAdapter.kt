package org.d3if0031.warungmie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0031.warungmie.data.Cart
import org.d3if0031.warungmie.databinding.ViewProductCheckoutBinding

class CheckOutAdapter() : ListAdapter<Cart, CheckOutAdapter.ViewHolder>(CartDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = getItem(position)
        holder.bind(cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewProductCheckoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewProductCheckoutBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }

        fun bind(cart: Cart) = binding.apply {
            this.cart = cart
            executePendingBindings()
        }
    }

}
