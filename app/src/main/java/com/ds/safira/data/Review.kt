package com.ds.safira.data

import java.util.*

data class Review(
    val imageUrl: String,
    val description: String,
    val reporter: String,
    val latitude: Double,
    val longitude: Double,
    val createdAt: Date
)