package org.d3if0031.warungmie.data

data class Cart(
    val id: String,
    val product: Product,
    var quantity: Int = 0,
)
