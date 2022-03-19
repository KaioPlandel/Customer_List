package com.plandel.customerlist.ui.newCustomer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.plandel.customerlist.api.RetrofitService
import com.plandel.customerlist.databinding.ActivityNewCustomerBinding
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import com.plandel.customerlist.util.Validator


class NewCustomerActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewCustomerBinding
    lateinit var viewModel: NewCustomerViewModel
    val retrofitService = RetrofitService.getRetrofitInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            NewCustomerViewModelFactory(CustomerRepository(retrofitService))
        ).get(NewCustomerViewModel::class.java)

        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.status.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                binding.progressSave.visibility = View.GONE
                enableInputAndClick()
                finish()
            } else {
                enableInputAndClick()
                binding.progressSave.visibility = View.GONE
                Toast.makeText(this, "Error to add new customer try again!", Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }

    private fun setupListeners() {

        binding.buttonAddClient.setOnClickListener {

            Log.d("TAG,", "setupListeners: click")
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val phone = binding.editPhone.text.toString()

            if (Validator.checkInputs(name, email, phone)) {
                disableInputsAndClick()
                binding.progressSave.visibility = View.VISIBLE
                val newCustomer = CustomerItem(email, "", name, phone)
                viewModel.addNewCustomer(newCustomer)
            } else {
                Toast.makeText(this, "fill out all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonBack.setOnClickListener { finish() }

    }

    private fun disableInputsAndClick() {
        binding.editName.isEnabled = false
        binding.editEmail.isEnabled = false
        binding.editPhone.isEnabled = false
        binding.buttonAddClient.isClickable = false
    }

    private fun enableInputAndClick() {
        binding.editName.isEnabled = true
        binding.editEmail.isEnabled = true
        binding.editPhone.isEnabled = true
        binding.buttonAddClient.isClickable = true
    }
}