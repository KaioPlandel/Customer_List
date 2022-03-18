package com.plandel.customerlist.repository

import com.plandel.customerlist.api.RetrofitService
import com.plandel.customerlist.model.CustomerItem

class CustomerRepository(private val retrofitService: RetrofitService){

    fun getAllCustomers() =  retrofitService.getAllCustomers()

    fun addNewCustomer(customerItem: CustomerItem) = retrofitService.addNewCustomer(customerItem)

}