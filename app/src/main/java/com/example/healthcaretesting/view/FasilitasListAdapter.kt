package com.example.healthcaretesting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FasilitasListItemBinding
import com.example.healthcaretesting.model.Facility
import com.example.healthcaretesting.model.Medicine

class FacilityListAdapter(private val fasilitas:ArrayList<Facility>) : RecyclerView.Adapter<FacilityListAdapter.FacilityViewHolder>(), FacilityListFragmentInterface {

    class FacilityViewHolder(val view:FasilitasListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):FacilityViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FasilitasListItemBinding>(inflater, R.layout.fasilitas_list_item, parent, false)
        return FacilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fasilitas.size
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int){
//        holder.view.medicine = fasilitas[position]
        //holder.view.seeIngredientsListener  = this
    }



    override fun onDetailClick(view: View) {
        TODO("Not yet implemented")
    }
}