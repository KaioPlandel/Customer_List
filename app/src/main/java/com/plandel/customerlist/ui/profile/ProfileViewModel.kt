package com.plandel.customerlist.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel constructor(private val repository: CustomerRepository) : ViewModel() {

    var status = MutableLiveData<Boolean>()
    var message = MutableLiveData<String>()


    //delete
    fun deleteCustomer(id: Int) {
        var request = repository.deleteCustomer(id)

        request.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    status.postValue(true)
                    message.postValue("Success!")
                } else {
                    message.postValue(response.message())
                    status.postValue(true)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                message.postValue(t.message)
                status.postValue(false)
            }

        })

    }

    //update
    fun updateCustomer(customerItem: CustomerItem) {

        val request = repository.updateCustomer(customerItem)
        request.enqueue(object : Callback<CustomerItem> {
            override fun onResponse(call: Call<CustomerItem>, response: Response<CustomerItem>) {
               if(response.isSuccessful){
                   status.postValue(true)
               }else {
                   status.postValue(true)
                   Log.d("TAG", "onResponse: " + response.message())
               }
            }

            override fun onFailure(call: Call<CustomerItem>, t: Throwable) {
                message.postValue(t.message)
            }

        })
    }
}