package com.example.kotlinexample.models

data class RecyclerViewItemModel(
    val currencyImage: Int,
    val currencyName: String,
    val currencyCode: String,
    val buyRate: Double,
    val sellRate: Double,
    val dailyChange: Double
)
