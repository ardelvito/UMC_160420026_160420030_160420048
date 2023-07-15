package com.example.healthcaretesting.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.viewmodel.ProfileDetailViewModel


class MyProfileDetailFragment : Fragment() {

    private lateinit var viewModel: ProfileDetailViewModel
    private lateinit var userIdPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Change text in toolbar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Edit Profile"

        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)

        viewModel = ViewModelProvider(this).get(ProfileDetailViewModel::class.java)
        viewModel.fetch(userId)
        observeViewModel()

        val btnConfirmEditProfile = view.findViewById<Button>(R.id.btnConfirmEditProfile)
        btnConfirmEditProfile.setOnClickListener{
            val txtFullnameDetail = view.findViewById<EditText>(R.id.txtFullnameDetail)
            val txtUsernameDetail = view.findViewById<EditText>(R.id.txtUsernameDetail)
            val txtPhoneDetail = view.findViewById<EditText>(R.id.txtPhoneDetail)


            viewModel.updateUser(
                txtFullnameDetail.text.toString(),
                txtUsernameDetail.text.toString(),
                txtPhoneDetail.text.toString(),
                userId
            )

            Toast.makeText(requireContext(), "Update successful", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()



        }

        val btnChangePassword = view.findViewById<Button>(R.id.btnChangePassword)
        btnChangePassword.setOnClickListener{
            val action = MyProfileDetailFragmentDirections.actionChangePassword()
            Navigation.findNavController(it).navigate(action)
        }


    }

    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            val txtFullnameDetail = view?.findViewById<EditText>(R.id.txtFullnameDetail)
            val txtUsernameDetail = view?.findViewById<EditText>(R.id.txtUsernameDetail)
            val txtPhoneDetail = view?.findViewById<EditText>(R.id.txtPhoneDetail)

            txtFullnameDetail?.setText(it.fullname)
            txtUsernameDetail?.setText(it.username)
            txtPhoneDetail?.setText(it.phone)


        }
    }

}