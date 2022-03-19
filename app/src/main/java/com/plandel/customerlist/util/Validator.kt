package com.plandel.customerlist.util

import android.widget.Toast

object Validator {

     fun checkInputs(name: String, email: String, phone: String): Boolean {

        if (!name.isEmpty()) {
            if (!email.isEmpty() && email.contains("@") && !email.contains(" ") && email.contains(".")) {
                return !phone.isEmpty()
            } else {
                return false
            }
        } else {
            return false
        }
    }

}