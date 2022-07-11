package org.d3if0031.warungmie.data

import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val username: String = "",
    val email: String = "",
    val password: String = "",
)
