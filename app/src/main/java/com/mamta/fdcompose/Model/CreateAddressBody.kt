package com.mamta.fdcompose.Model

data class CreateAddressBody(
    val address: String,
    val area: String,
    val c_id: Int,
    val city: String,
    val first_name: String,
    val landmark: String,
    val last_name: String,
    val lat: String,
    val long: String,
    val pincode: String,
    val state: String
)