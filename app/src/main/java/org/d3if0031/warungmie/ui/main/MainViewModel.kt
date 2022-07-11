package org.d3if0031.warungmie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0031.warungmie.R
import org.d3if0031.warungmie.data.Cart
import org.d3if0031.warungmie.data.InMemoryCartDataStore
import org.d3if0031.warungmie.data.Product

class MainViewModel : ViewModel() {

    val products = listOf(
        Product(
            name = "Indomie Kari Ayam",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak",
            imageId = R.drawable.kari_ayam,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
        Product(
            name = "Indomie Ayam Bawang",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak",
            imageId = R.drawable.ayam_bawang,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
        Product(
            name = "Indomie Ayam Spesial",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak",
            imageId = R.drawable.ayam_spesial,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
        Product(
            name = "Indomie Mie Goreng",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak\n- Kecap\n- Bawang Goreng",
            imageId = R.drawable.mi_goreng,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
        Product(
            name = "Indomie Mie Goreng Cabe Ijo",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak\n- Kecap\n- Bawang Goreng",
            imageId = R.drawable.mie_goreng_cabe_ijo,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
        Product(
            name = "Indomie Mie Goreng Hot Spicy",
            description = "Komposisi:\n- Tepung Terigu\n- Cabai\n- Penyedap rasa\n- Minyak\n- Kecap\n- Bawang Goreng",
            imageId = R.drawable.mie_goreng_hot_spicy,
            textPrice = "Rp. 5.000",
            price = 5000F,
        ),
    )

    private val inMemoryCart = InMemoryCartDataStore

    private val _cart = MutableLiveData<List<Cart>>()
    val cart: LiveData<List<Cart>> = _cart

    private val _selectedProduct = MutableLiveData<Product?>()
    val selectedProduct: LiveData<Product?> = _selectedProduct

    fun setSelectedProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun clearSelectedProduct() {
        _selectedProduct.value = null
    }

    fun addToCart(product: Product) {
        inMemoryCart.add(product)
        _cart.postValue(inMemoryCart.get())
    }

    fun updateQuantity(productId: String, quantity: Int) {
        inMemoryCart.updateQuantity(productId, quantity)
        _cart.postValue(inMemoryCart.get())
    }

    fun removeCartProduct(productId: String) {
        inMemoryCart.remove(productId)
        _cart.postValue(inMemoryCart.get())
    }

    fun clearCart() {
        inMemoryCart.clear()
        _cart.postValue(inMemoryCart.get())
    }

}