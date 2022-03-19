package com.plandel.customerlist.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    //update
    private fun setupListeners() {
        binding.buttonUpdateClient.setOnClickListener {
            val customer = intent.getSerializableExtra("customer") as CustomerItem
            val name = binding.editNameUpdate.text.toString()
            val email = binding.editEmailUpdate.text.toString()
            val phone = binding.editPhoneUpdate.text.toString()

            if (Validator.checkInputs(name, email, phone)) {
                binding.progressProfile.visibility = View.VISIBLE
                disableInputsAndClick()
                val newCustomer = CustomerItem(email, customer.id, name, phone)
                viewModel.updateCustomer(newCustomer)
                finish()
            }else {
                Toast.makeText(this, "fill out all fields correctly", Toast.LENGTH_SHORT).show()
            }
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

    private fun disableInputsAndClick() {
        binding.editNameUpdate.isEnabled = false
        binding.editEmailUpdate.isEnabled = false
        binding.editPhoneUpdate.isEnabled = false
        binding.buttonUpdateClient.isClickable = false
    }

    private fun enableInputAndClick() {
        binding.editNameUpdate.isEnabled = true
        binding.editEmailUpdate.isEnabled = true
        binding.editPhoneUpdate.isEnabled = true
        binding.buttonUpdateClient.isClickable = true
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
                Toast.makeText(this, "Error, Try Again!", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.message.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

    private fun deleteCustomer(id: Int) {
        viewModel.deleteCustomer(id)
    }


}