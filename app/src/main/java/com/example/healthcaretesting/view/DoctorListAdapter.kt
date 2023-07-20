package com.example.healthcaretesting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.DoctorListItemBinding
import com.example.healthcaretesting.model.Doctor

class DoctorListAdapter(val doctors: ArrayList<Doctor>) : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>(), DoctorListFragmentInterface {

    class DoctorViewHolder(val view: DoctorListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorListAdapter.DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        val view = DataBindingUtil.inflate<DoctorListItemBinding>(inflater, R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorListAdapter.DoctorViewHolder, position: Int) {

        holder.view.doctor = doctors[position]
        holder.view.doctorListFragmentInterface = this

//        var doctorName = holder.view.findViewById<TextView>(R.id.txtDoctorName)
//        var doctorHospital = holder.view.findViewById<TextView>(R.id.txtDoctorHospital)
//        var doctorSpecialitiy = holder.view.findViewById<TextView>(R.id.txtDoctorSpeciality)
//
//        var url_img = doctors[position].url_img
//        var doctorImageView = holder.view.findViewById<ImageView>(R.id.imgDoctor)
//        var doctorProgessBar = holder.view.findViewById<ProgressBar>(R.id.progressBarDoctorList)
//
//
//        var btnBooking = holder.view.findViewById<Button>(R.id.btnBookingDoctor)
//        btnBooking.setOnClickListener {
//            val action = DoctorListFragmentDirections.actionDoctorDetail(doctors[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        Picasso.get()
//            .load(url_img)
//            .resize(200, 200)
//            .centerCrop()
//            .into(doctorImageView, object : Callback {
//                override fun onSuccess() {
//                    doctorProgessBar?.visibility = View.GONE
//                    Log.d("Success Load", "IMG Successfully Loaded")
//
//                }
//
//                override fun onError(e: Exception?) {
//                    // Handle error
//                    doctorProgessBar?.visibility = View.GONE
//                    Log.d("Error Load", e.toString())
//                }
//
//            })
//
//        doctorName.text = doctors[position].fullname
//        doctorHospital.text = doctors[position].hospital
//        doctorSpecialitiy.text = doctors[position].speciality
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    fun updateDoctorList(newDoctor: ArrayList<Doctor>){
        doctors.clear()
        doctors.addAll(newDoctor)
        notifyDataSetChanged()
    }

    override fun onDoctorDetailClick(view: View) {
        val doctorId = view.tag.toString().toInt()
        val action = DoctorListFragmentDirections.actionDoctorDetail(doctorId)
        Navigation.findNavController(view).navigate(action)
    }
}