package com.plandel.customerlist.api

import com.plandel.customerlist.model.CustomerItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("Project/app/customers")
    fun getAllCustomers(): Call<List<CustomerItem>>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://622e73708d943bae34955025.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getRetrofitInstance(): RetrofitService {
            return retrofitService
        }

    }

}