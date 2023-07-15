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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.viewmodel.ConfirmationPasswordViewModel


class ConfirmationPasswordFragment : Fragment() {

    private lateinit var viewModel: ConfirmationPasswordViewModel
    private lateinit var userIdPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Change text in toolbar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Confirmation Password"



        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)
        viewModel = ViewModelProvider(this).get(ConfirmationPasswordViewModel::class.java)

        val btnConfirmChangePassword = view.findViewById<Button>(R.id.btnConfirmPassword)
        val password = view.findViewById<EditText>(R.id.txtPasswordConfirmation)

        btnConfirmChangePassword.setOnClickListener{
            viewModel.confirmUserPassword(userId, password.text.toString())
            viewModel.isValidPassword.observe(viewLifecycleOwner){ isValid ->
                if(isValid){
                    Toast.makeText(requireContext(), "Confirmation Password success", Toast.LENGTH_SHORT).show()
                    val action = ConfirmationPasswordFragmentDirections.actionNewPassword()
                    Navigation.findNavController(it).navigate(action)
                }
                else if(isValid == false){
                    Toast.makeText(requireContext(), "Confirmation Password failed", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }


}