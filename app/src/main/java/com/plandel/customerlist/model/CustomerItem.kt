package com.plandel.customerlist.model

import java.io.Serializable

data class CustomerItem(
    val email: String,
    val id: String,
    val name: String,
    val phone: String
): Serializable