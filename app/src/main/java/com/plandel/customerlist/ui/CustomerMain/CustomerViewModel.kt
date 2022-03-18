package com.plandel.customerlist.ui.CustomerMain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel constructor(private val repository: CustomerRepository) : ViewModel() {

    val listOfCustomers = MutableLiveData<List<CustomerItem>>()
    val errorMensage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getAllCustomers() {

        val request = repository.getAllCustomers()

        request.enqueue(object : Callback<List<CustomerItem>> {
            override fun onResponse(
                call: Call<List<CustomerItem>>,
                response: Response<List<CustomerItem>>
            ) {
                if (response.isSuccessful) {
                    loading.postValue(false)
                } else {
                    loading.postValue(false)
                    errorMensage.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<List<CustomerItem>>, t: Throwable) {
                loading.postValue(false)
                errorMensage.postValue(t.message)
            }

        })

    }

}