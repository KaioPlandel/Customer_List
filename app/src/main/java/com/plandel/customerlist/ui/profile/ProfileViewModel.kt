package com.plandel.customerlist.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel constructor(private val repository: CustomerRepository) : ViewModel() {
    var status = MutableLiveData<Boolean>()

    //delete
    fun deleteCustomer(id: Int) {
        val request = repository.deleteCustomer(id)
        request.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    status.postValue(true)
                } else {
                    status.postValue(true)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                status.postValue(false)
            }
        })
    }

    //update
    fun updateCustomer(customerItem: CustomerItem) {
        val request = repository.updateCustomer(customerItem)
        request.enqueue(object : Callback<CustomerItem> {
            override fun onResponse(call: Call<CustomerItem>, response: Response<CustomerItem>) {
                if (response.isSuccessful) {
                    status.postValue(true)
                } else {
                    status.postValue(true)
                    Log.d("TAG", "onResponse: " + response.message())
                }
            }

            override fun onFailure(call: Call<CustomerItem>, t: Throwable) {
                status.postValue(false)
            }
        })
    }

    fun callTo(context: Context,number: String){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel: $number")
        context.startActivity(intent)
    }

    fun emailTo(context: Context, email: String){
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email,null))
        context.startActivity(intent)
    }

}