package com.example.healthcaretesting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.MedicineListItemBinding
import com.example.healthcaretesting.model.Medicine

class MedicineListAdapter(private val medicines:ArrayList<Medicine>) : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>(), MedicineListFragmentInterface {

    class MedicineViewHolder(val view:MedicineListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):MedicineViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MedicineListItemBinding>(inflater, R.layout.medicine_list_item, parent, false)
        return MedicineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return medicines.size
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int){
        holder.view.medicine = medicines[position]
        holder.view.seeIngredientsListener  = this
    }

    override fun onSeeIngredientsClick(view: View) {
        val id = view.tag.toString().toInt()
        val action = MedicineListFragmentDirections.actionMedicineDetail(id)
        Navigation.findNavController(view).navigate(action)
    }
}