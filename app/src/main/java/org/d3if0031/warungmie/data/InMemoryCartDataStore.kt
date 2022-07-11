package org.d3if0031.warungmie.data

object InMemoryCartDataStore {

    private val cart = mutableMapOf<String, Cart>()

    fun get(): List<Cart> {
        return cart.values.toList()
    }

    fun add(product: Product) {
        val cartProduct = cart[product.id]

        if (cartProduct != null) {
            cartProduct.quantity += 1
            return
        }

        cart[product.id] = Cart(product.id, product, 1)
    }

    fun updateQuantity(productId: String, quantity: Int) {
        cart[productId]?.quantity = quantity
    }

    fun remove(productId: String) {
        cart.remove(productId)
    }

    fun clear() {
        cart.clear()
    }
}