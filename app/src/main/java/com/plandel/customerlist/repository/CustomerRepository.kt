package com.plandel.customerlist.repository

import com.plandel.customerlist.api.RetrofitService
import com.plandel.customerlist.model.CustomerItem

class CustomerRepository(private val retrofitService: RetrofitService) {
    fun getAllCustomers() = retrofitService.getAllCustomers()

    fun addNewCustomer(customerItem: CustomerItem) = retrofitService.addNewCustomer(customerItem)

    fun updateCustomer(customerItem: CustomerItem) = retrofitService.updateCustomer(
        Integer.parseInt(customerItem.id),
        customerItem.name,
        customerItem.email,
        customerItem.phone
    )

    fun deleteCustomer(id: Int) = retrofitService.deleteCustomer(id)
}