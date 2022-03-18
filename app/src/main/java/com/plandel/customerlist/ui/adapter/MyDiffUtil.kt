package com.plandel.customerlist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.plandel.customerlist.model.CustomerItem

class MyDiffUtil(
    private val oldList: List<CustomerItem>,
    private val newList: List<CustomerItem>

) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].email != newList[newItemPosition].email-> {
                false
            }
            oldList[oldItemPosition].phone != newList[newItemPosition].phone-> {
                false
            }

            else -> true
        }
    }
}