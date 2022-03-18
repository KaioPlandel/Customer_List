package com.plandel.customerlist.ui.newCustomer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plandel.customerlist.R
import com.plandel.customerlist.databinding.ActivityNewCustomerBinding

class NewCustomerActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}