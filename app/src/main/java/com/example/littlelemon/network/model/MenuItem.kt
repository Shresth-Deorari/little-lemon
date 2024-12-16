package com.example.littlelemon.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)