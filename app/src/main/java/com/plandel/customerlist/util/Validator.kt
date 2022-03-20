package com.plandel.customerlist.util

object Validator {
    fun checkEmail(email: String): Boolean {
        return email.isNotEmpty() && email.contains("@") && !email.contains(" ") && email.contains(".")
    }

    fun checkName(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun checkPhone(phone: String): Boolean {
        return (phone.isNotEmpty() && phone.length >= 10)
    }
}