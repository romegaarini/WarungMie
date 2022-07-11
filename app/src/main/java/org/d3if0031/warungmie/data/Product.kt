package org.d3if0031.warungmie.data

import java.util.*

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val imageId: Int = 0,
    val textPrice: String = "",
    val price: Float = 0F,
)
