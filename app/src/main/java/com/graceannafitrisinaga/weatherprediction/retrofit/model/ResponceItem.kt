package com.graceannafitrisinaga.weatherprediction.retrofit.model

data class ResponceItem(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)