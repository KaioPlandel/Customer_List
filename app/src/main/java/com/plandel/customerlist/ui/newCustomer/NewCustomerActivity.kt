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

            disableInputsAndClick()
            Log.d("TAG," ,"setupListeners: click")
            var name = binding.editName.text.toString()
            var email = binding.editEmail.text.toString()
            var phone = binding.editPhone.text.toString()

            if (checkInputs(name, email, phone)) {
                binding.progressSave.visibility = View.VISIBLE
                var newCustomer = CustomerItem(email, "", name, phone)
                viewModel.addNewCustomer(newCustomer)
            }
        }

        binding.buttonBack.setOnClickListener { finish() }

    }

    private fun checkInputs(name: String, email: String, phone: String): Boolean {

        if (!name.isEmpty()) {
            if (!email.isEmpty() && email.contains("@")) {
                if (!phone.isEmpty()) {
                    return true
                } else {
                    Toast.makeText(this, "fill in the phone field!", Toast.LENGTH_SHORT).show()
                    return false
                }
            } else {
                Toast.makeText(this, "fill in the email field!", Toast.LENGTH_SHORT).show()
                return false
            }
        } else {
            Toast.makeText(this, "fill in the name field!", Toast.LENGTH_SHORT).show()
            return false
        }
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