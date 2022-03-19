package com.plandel.customerlist.ui.CustomerMain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.plandel.customerlist.api.RetrofitService
import com.plandel.customerlist.databinding.ActivityCustomerBinding
import com.plandel.customerlist.repository.CustomerRepository
import com.plandel.customerlist.ui.adapter.CustomerAdapter
import com.plandel.customerlist.ui.newCustomer.NewCustomerActivity


class CustomerActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomerBinding
    lateinit var viewModel: CustomerViewModel
    val retrofitService = RetrofitService.getRetrofitInstance()
    var adapter = CustomerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            CustomerViewModelFactory(CustomerRepository(retrofitService))
        ).get(CustomerViewModel::class.java)


        setupListeners()
        setupRecyclerView()
        setupObservers()

    }

    override fun onStart() {
        super.onStart()

        viewModel.listOfCustomers.observe(this, Observer {
            adapter.setData(it)
        })

        viewModel.getAllCustomers()

    }

    private fun setupObservers() {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })

        viewModel.errorScreen.observe(this, Observer {

            if (it) {
                showScreenError()
            } else {
                hideScreenError()
            }

        })
    }

    private fun setupRecyclerView() {

        binding.recyclercustomer.layoutManager = LinearLayoutManager(this)
        binding.recyclercustomer.adapter = adapter
    }

    private fun setupListeners() {
        binding.buttonNewClient.setOnClickListener {
            Intent(this, NewCustomerActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.buttonReload.setOnClickListener {
            binding.progressMenu.visibility = View.VISIBLE
            viewModel.getAllCustomers()
        }

    }

    private fun hideScreenError() {
        binding.recyclercustomer.visibility = View.VISIBLE
        binding.layoutError.visibility = View.GONE
        binding.progressMenu.visibility = View.GONE
    }

    private fun showScreenError() {
        binding.recyclercustomer.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.progressMenu.visibility = View.VISIBLE
        binding.recyclercustomer.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressMenu.visibility = View.GONE
        binding.recyclercustomer.visibility = View.VISIBLE
    }
}