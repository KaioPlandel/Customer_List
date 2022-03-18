package com.plandel.customerlist.repository

import com.plandel.customerlist.api.RetrofitService

class CustomerRepository constructor(private val retrofitService: RetrofitService){

    fun getAllCustomers() =  retrofitService.getAllCustomers()

}