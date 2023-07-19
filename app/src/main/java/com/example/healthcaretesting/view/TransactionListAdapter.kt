package com.example.healthcaretesting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.TransactionListItemBinding
import com.example.healthcaretesting.model.Booking

class TransactionListAdapter (private val transaction: ArrayList<Booking>):
RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>(), TransactionListFragmentInterface{
    class TransactionViewHolder(val view:TransactionListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TransactionListItemBinding>(inflater, R.layout.transaction_list_item,parent,false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int = transaction.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.view.booking = transaction[position]
        holder.view.detailListener = this
    }

    fun UpdateList(newList:ArrayList<Booking>){
        transaction.clear()
        transaction.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onDetailClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = TransactionListFragmentDirections.actionTransactionListTransactionDetail(id)
        Navigation.findNavController(v).navigate(action)
    }

}