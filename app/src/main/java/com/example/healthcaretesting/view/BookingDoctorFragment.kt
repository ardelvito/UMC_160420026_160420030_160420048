package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FragmentBookingDoctorBinding
import com.example.healthcaretesting.model.Doctor
import com.example.healthcaretesting.viewmodel.BookingDoctorViewModel

class BookingDoctorFragment : Fragment(), BookingDoctorFragmentInterface {

    private lateinit var viewModel: BookingDoctorViewModel
    var selectedWorkingDay: String = ""
    var selectedWorkingHour: String = ""
        private lateinit var dataBinding: FragmentBookingDoctorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentBookingDoctorBinding>(inflater, R.layout.fragment_booking_doctor, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_booking_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctorId = BookingDoctorFragmentArgs.fromBundle(requireArguments()).uuid

        viewModel = ViewModelProvider(this).get(BookingDoctorViewModel::class.java)
        viewModel.fetchSchedule(doctorId)
        observeViewModel()

//        val btn = view.findViewById<Button>(R.id.btnConfirmSchedule)
//        btn.setOnClickListener {
//            val action = BookingDoctorFragmentDirections.actionBookingConfirmation(doctorId, selectedWorkingDay, selectedWorkingHour)
//            Navigation.findNavController(view).navigate(action)
//        }
//
//        Log.d("RECEIVE ID " , BookingDoctorFragmentArgs.fromBundle(requireArguments()).uuid.toString())

        dataBinding.bookingDoctorInterface = this

    }

    private fun observeViewModel() {

        viewModel.doctorLiveData.observe(viewLifecycleOwner){
            dataBinding.doctor = it
//            val workdaysSpinner: Spinner? = view?.findViewById(R.id.spinnerWorkingDays)
//            val workhoursSpinner: Spinner? = view?.findViewById(R.id.spinnerWorkingHours)
//
//            val workingDays = it.working_days
//            val workingHours = it.working_hours
//
//            val workingDaysList = workingDays.split(", ")
//            val workingHoursList = workingHours.split(", ")
//
//            val workdaysAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, workingDaysList)
//            val workhoursAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, workingHoursList)
//
//            workdaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            workhoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//            workdaysSpinner?.adapter = workdaysAdapter
//            workhoursSpinner?.adapter = workhoursAdapter
//
//
//
//            workdaysSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    selectedWorkingDay = workdaysSpinner?.getItemAtPosition(position) as String
//                    // Use the selectedWorkingDay value as needed
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    // Handle the case where nothing is selected
//                }
//            }
//
//            workhoursSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    selectedWorkingHour = workhoursSpinner?.getItemAtPosition(position) as String
//                    // Use the selectedWorkingDay value as needed
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    // Handle the case where nothing is selected
//                }
//            }



        }
    }

    override fun onBookingNow(view: View, obj: Doctor) {

        val daySelected = dataBinding.spinnerWorkingDays.selectedItem.toString()
        val hourSelected = dataBinding.spinnerWorkingHours.selectedItem.toString()
        val doctorId = BookingDoctorFragmentArgs.fromBundle(requireArguments()).uuid


        Toast.makeText(requireContext(), "Selected Schedule: $daySelected, $hourSelected", Toast.LENGTH_SHORT).show()
        val action = BookingDoctorFragmentDirections.actionBookingConfirmation(doctorId, daySelected, hourSelected)
        Navigation.findNavController(view).navigate(action)

    }


}