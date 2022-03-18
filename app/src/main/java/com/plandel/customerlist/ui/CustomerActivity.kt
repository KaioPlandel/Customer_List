package com.plandel.customerlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plandel.customerlist.R
import com.plandel.customerlist.databinding.ActivityCustomerBinding


class CustomerActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

    }

    private fun setupListeners() {
        binding.buttonNewClient.setOnClickListener {
            Intent(this, NewCustomerActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}