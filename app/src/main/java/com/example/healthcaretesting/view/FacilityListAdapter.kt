package com.example.healthcaretesting.view

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FasilitasListItemBinding
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.model.Facility

class FacilityListAdapter(private val facilities:ArrayList<Facility>) : RecyclerView.Adapter<FacilityListAdapter.FacilityViewHolder>(), FacilityListFragmentInterface {

    class FacilityViewHolder(val view:FasilitasListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):FacilityViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FasilitasListItemBinding>(inflater, R.layout.fasilitas_list_item, parent, false)
        return FacilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int){
        holder.view.facility = facilities[position]
        holder.view.detailListenerFacility = this
    }



    override fun onDetailClick(view: View) {
        TODO("Not yet implemented")
    }

    fun updateFacilityList(newFacilities: ArrayList<Facility>){
        facilities.clear()
        facilities.addAll(newFacilities)
        notifyDataSetChanged()
    }

}