package com.mamta.fdcompose.Model

data class AddressResponseData(
    val address: Address
)
data class Address(
    val address: String,
    val area: String,
    val c_id: Int,
    val city: String,
    val created_at: String,
    val first_name: String,
    val id: Int,
    val landmark: String,
    val last_name: String,
    val lat: String,
    val long: String,
    val mobile: Any,
    val pincode: String,
    val state: String,
    val updated_at: String
)