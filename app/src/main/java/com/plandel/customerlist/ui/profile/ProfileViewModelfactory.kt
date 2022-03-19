package com.plandel.customerlist.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plandel.customerlist.repository.CustomerRepository

class ProfileViewModelfactory constructor(private val repository: CustomerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }else {
            throw IllegalArgumentException("View Model Not Found!")
        }
    }
}