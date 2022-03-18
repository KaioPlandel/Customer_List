package com.plandel.customerlist.ui.CustomerMain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel constructor(private val repository: CustomerRepository) : ViewModel() {

    val listOfCustomers = MutableLiveData<List<CustomerItem>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val errorScreen = MutableLiveData<Boolean>()

    fun getAllCustomers() {

        val request = repository.getAllCustomers()

        request.enqueue(object : Callback<List<CustomerItem>> {
            override fun onResponse(
                call: Call<List<CustomerItem>>,
                response: Response<List<CustomerItem>>
            ) {
                if (response.isSuccessful) {
                    loading.postValue(false)
                    Log.d("TAG", "onResponse: " + response.message())
                    listOfCustomers.postValue(response.body())
                    errorScreen.postValue(false)
                } else {
                    loading.postValue(false)
                    Log.d("TAG", "onResponse2: " + response.message())
                    errorMessage.postValue(response.message())
                    errorScreen.postValue(true)
                }
            }

            override fun onFailure(call: Call<List<CustomerItem>>, t: Throwable) {
                loading.postValue(false)
                errorMessage.postValue(t.message)
                errorScreen.postValue(true)
            }

        })

    }

}