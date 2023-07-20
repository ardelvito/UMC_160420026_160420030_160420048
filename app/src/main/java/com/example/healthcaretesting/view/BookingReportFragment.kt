package com.example.healthcaretesting.view

import android.content.Context
import android.content.SharedPreferences
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
import com.example.healthcaretesting.databinding.FragmentBookingReportBinding
import com.example.healthcaretesting.model.Booking
import com.example.healthcaretesting.viewmodel.BookingReportViewModel


class BookingReportFragment : Fragment(), BookingReportFragmentInterface {

    private lateinit var viewModel: BookingReportViewModel
    private lateinit var userIdPreferences: SharedPreferences
    private lateinit var dataBinding: FragmentBookingReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentBookingReportBinding>(inflater, R.layout.fragment_booking_report, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_booking_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)

        viewModel = ViewModelProvider(this).get(BookingReportViewModel::class.java)

        val day = BookingReportFragmentArgs.fromBundle(requireArguments()).day
        val hour = BookingReportFragmentArgs.fromBundle(requireArguments()).hour
        val doctorId = BookingReportFragmentArgs.fromBundle(requireArguments()).uuid

        //Instantiate
        dataBinding.booking = Booking(userId, doctorId, day + " " + hour)
        dataBinding.bookingReportInterface = this

    }

    override fun onSubmitBooking(view: View, obj: Booking) {

        viewModel.insertBooking(obj).observe(viewLifecycleOwner, {isSuccess ->
            if(isSuccess){
                Toast.makeText(requireContext(), "Booking successful", Toast.LENGTH_SHORT).show()
                val action = BookingReportFragmentDirections.actionBackToDoctorList()
                Navigation.findNavController(view).navigate(action)
            }
            else{
                Toast.makeText(requireContext(), "Booking failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}