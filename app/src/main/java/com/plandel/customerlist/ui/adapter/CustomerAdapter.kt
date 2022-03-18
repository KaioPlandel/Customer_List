package com.plandel.customerlist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plandel.customerlist.R
import com.plandel.customerlist.model.CustomerItem

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.MyViewHolder>() {

    private var customerList = emptyList<CustomerItem>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.textName)
        var email = itemView.findViewById<TextView>(R.id.textEmail)
        var phone = itemView.findViewById<TextView>(R.id.textPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_customer_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var customer = customerList[position]
        holder.name.text = customer.name
        holder.email.text = customer.email
        holder.phone.text = customer.phone
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun setData(newListClient: List<CustomerItem>) {
        val diffUtil = MyDiffUtil(customerList, newListClient)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        customerList = newListClient
        diffResults.dispatchUpdatesTo(this)
    }

}