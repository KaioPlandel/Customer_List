package com.plandel.customerlist.ui.newCustomer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewCustomerViewModel constructor(private val repository: CustomerRepository) : ViewModel() {

    var status = MutableLiveData<Boolean>()

    fun addNewCustomer( customerItem: CustomerItem){

        var request = repository.addNewCustomer(customerItem)

        request.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    status.postValue(true)
                }else {
                    status.postValue(true)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
                status.postValue(false)
            }

        })

    }


}