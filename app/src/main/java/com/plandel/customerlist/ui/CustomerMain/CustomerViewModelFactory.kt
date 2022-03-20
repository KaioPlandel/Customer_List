package com.plandel.customerlist.ui.CustomerMain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plandel.customerlist.repository.CustomerRepository

class CustomerViewModelFactory constructor(private val repository: CustomerRepository) :
    ViewModelProvider.Factory {
    companion object {
        private const val TAG = "CustomerViewModel"
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found!")
        }
    }
}