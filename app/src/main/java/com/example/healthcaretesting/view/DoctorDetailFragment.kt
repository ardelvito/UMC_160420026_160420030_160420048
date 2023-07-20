package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FragmentDoctorDetailBinding
import com.example.healthcaretesting.viewmodel.DoctorDetailViewModel

class DoctorDetailFragment : Fragment(), DoctorDetailFragmentInterface {

    private lateinit var viewModel: DoctorDetailViewModel
    private lateinit var dataBinding: FragmentDoctorDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDoctorDetailBinding>(inflater, R.layout.fragment_doctor_detail, container, false)
        return dataBinding.root
        //        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)

        val uuidDoctor = DoctorDetailFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuidDoctor)
        observeViewModel()


        //Instantiate listener
        dataBinding.doctorDetailInterface = this


    }

    private fun observeViewModel() {
        viewModel.doctorLiveData.observe(viewLifecycleOwner){
            dataBinding.doctor = it

//            val txtDoctorName = view?.findViewById<TextView>(R.id.txtDoctorNameDetail)
//            val txtDoctorHospital = view?.findViewById<TextView>(R.id.txtDoctorHospitalDetail)
//            val txtDoctorSpeciality = view?.findViewById<TextView>(R.id.txtDoctorSpecialityDetail)
//            val txtWorkingHours = view?.findViewById<TextView>(R.id.txtDoctorWorkingHours)
//            val txtWorkingDays = view?.findViewById<TextView>(R.id.txtDoctorWorkingDays)
//
//            var url_img = it.url_img
//            var doctorImageView = view?.findViewById<ImageView>(R.id.imageViewDoctorDetail)
//            var doctorProgessBar = view?.findViewById<ProgressBar>(R.id.progressBarDoctorDetail)
//
//            Picasso.get()
//                .load(url_img)
//                .resize(200, 200)
//                .centerCrop()
//                .into(doctorImageView, object : Callback {
//                    override fun onSuccess() {
//                        doctorProgessBar?.visibility = View.GONE
//                        Log.d("Success Load", "IMG Successfully Loaded")
//
//                    }
//
//                    override fun onError(e: Exception?) {
//                        // Handle error
//                        doctorProgessBar?.visibility = View.VISIBLE
//                        Log.d("Error Load", e.toString())
//                    }
//
//                })
//
//            txtDoctorName?.text = it.fullname
//            txtDoctorHospital?.text = it.hospital
//            txtDoctorSpeciality?.text = it.speciality
//
//            val workingDaysList = it.working_days.split(", ")
//            val workingHoursList = it.working_hours.split(", ")
//
//                val concateWorkingDays = StringBuilder()
//                val concateWorkingHours = StringBuilder()
//
//                for (day in workingDaysList) {
//                    concateWorkingDays.append(day)
//                    concateWorkingDays.append("\n")
//                }
//
//                for (hours in workingHoursList) {
//                    concateWorkingHours.append(hours)
//                    concateWorkingHours.append("\n")
//                }
//
//                txtWorkingDays?.text = concateWorkingDays.toString()
//                txtWorkingHours?.text = concateWorkingHours.toString()

        }


    }

    override fun onBookingSchedule(view: View) {
        val uuidDoctor = DoctorDetailFragmentArgs.fromBundle(requireArguments()).uuid
        val action = DoctorDetailFragmentDirections.actionBookingDoctor(uuidDoctor)
        Navigation.findNavController(view).navigate(action)
    }
}