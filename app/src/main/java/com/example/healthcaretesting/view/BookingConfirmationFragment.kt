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
import com.example.healthcaretesting.databinding.FragmentBookingConfirmationBinding
import com.example.healthcaretesting.databinding.FragmentConfirmationPasswordBinding
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.viewmodel.BookingConfirmationPasswordViewModel
import com.example.healthcaretesting.viewmodel.ConfirmationPasswordViewModel

class BookingConfirmationFragment : Fragment(), BookingConfirmationFragmentInterface {

    private lateinit var viewModel: BookingConfirmationPasswordViewModel
    private lateinit var userIdPreferences: SharedPreferences
    private lateinit var dataBinding: FragmentBookingConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentBookingConfirmationBinding>(inflater, R.layout.fragment_booking_confirmation, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_booking_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)
        viewModel = ViewModelProvider(this).get(BookingConfirmationPasswordViewModel::class.java)

        //Instantiate
        dataBinding.user = User("", "", "", "", "", 0)
        dataBinding.bookingConfirmationPasswordInterface = this


    }

    override fun onConfirmPasswordBooking(view: View, obj: User) {
        val userId = userIdPreferences.getInt("userId", 0)
        val password = obj.password.toString()
        val doctorId = BookingConfirmationFragmentArgs.fromBundle(requireArguments()).uuid
        val day = BookingConfirmationFragmentArgs.fromBundle(requireArguments()).day
        val hour = BookingConfirmationFragmentArgs.fromBundle(requireArguments()).hour

        viewModel.confirmUserPassword(userId, password)
        viewModel.isValidPassword.observe(viewLifecycleOwner){ isValid ->
            if(isValid){
                Toast.makeText(requireContext(), "Confirmation Password success", Toast.LENGTH_SHORT).show()
                val action = BookingConfirmationFragmentDirections.actionBookingReport(doctorId, day, hour)
                Navigation.findNavController(view).navigate(action)

            }
            else if(isValid == false){
                Toast.makeText(requireContext(), "Confirmation Password failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}