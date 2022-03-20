package com.plandel.customerlist.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.plandel.customerlist.R
import com.plandel.customerlist.api.RetrofitService
import com.plandel.customerlist.databinding.ActivityProfileBinding
import com.plandel.customerlist.model.CustomerItem
import com.plandel.customerlist.repository.CustomerRepository
import com.plandel.customerlist.util.Validator

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private val retrofitService = RetrofitService.getRetrofitInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ProfileViewModelfactory(CustomerRepository(retrofitService))
        ).get(ProfileViewModel::class.java)
        initViews()
        setupListeners()
        setupObservers()
    }

    private fun initViews() {
        val customer = intent.getSerializableExtra("customer") as CustomerItem
        binding.editNameUpdate.setText(customer.name)
        binding.editEmailUpdate.setText(customer.email)
        binding.editPhoneUpdate.setText(customer.phone)
    }

    private fun setupListeners() {
        //update
        binding.buttonUpdateClient.setOnClickListener {
            val customer = intent.getSerializableExtra("customer") as CustomerItem
            val name = binding.editNameUpdate.text.toString()
            val email = binding.editEmailUpdate.text.toString()
            val phone = binding.editPhoneUpdate.text.toString()
            if (!Validator.checkName(name)) {
                binding.editNameUpdate.error = "Name required"
                binding.editNameUpdate.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.checkEmail(email)) {
                binding.editEmailUpdate.error = "Invalid email address"
                binding.editEmailUpdate.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.checkPhone(phone)) {
                binding.editPhoneUpdate.error = "Must be minimum 10 digits"
                binding.editPhoneUpdate.requestFocus()
                return@setOnClickListener
            }
            binding.progressProfile.visibility = View.VISIBLE
            disableInputsAndClick()
            val newCustomer = CustomerItem(email, customer.id, name, phone)
            viewModel.updateCustomer(newCustomer)
        }

        //delete
        binding.buttonDelete.setOnClickListener {
            binding.progressProfile.visibility = View.VISIBLE
            disableInputsAndClick()
            val customer = intent.getSerializableExtra("customer") as CustomerItem
            deleteCustomer(Integer.parseInt(customer.id))
        }
        binding.buttonUpdateBack.setOnClickListener { finish() }
    }

    private fun setupObservers() {
        viewModel.status.observe(this, Observer {
            if (it) {
                enableInputAndClick()
                binding.progressProfile.visibility = View.GONE
                finish()
            } else {
                enableInputAndClick()
                binding.progressProfile.visibility = View.GONE
                Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun deleteCustomer(id: Int) {
        viewModel.deleteCustomer(id)
    }

    private fun disableInputsAndClick() {
        binding.editNameUpdate.isEnabled = false
        binding.editEmailUpdate.isEnabled = false
        binding.editPhoneUpdate.isEnabled = false
        binding.buttonUpdateClient.isClickable = false
        binding.buttonDelete.isClickable = false
    }

    private fun enableInputAndClick() {
        binding.editNameUpdate.isEnabled = true
        binding.editEmailUpdate.isEnabled = true
        binding.editPhoneUpdate.isEnabled = true
        binding.buttonUpdateClient.isClickable = true
        binding.buttonDelete.isClickable = true
    }
}