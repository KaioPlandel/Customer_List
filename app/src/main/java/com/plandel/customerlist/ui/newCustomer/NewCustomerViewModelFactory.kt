package com.plandel.customerlist.ui.newCustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plandel.customerlist.repository.CustomerRepository

class NewCustomerViewModelFactory constructor(private val repository: CustomerRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewCustomerViewModel::class.java)) {
            return NewCustomerViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found!")
        }
    }
}