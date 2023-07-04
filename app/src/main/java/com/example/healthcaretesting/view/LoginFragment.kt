package com.example.healthcaretesting.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Hide bottom nav, drawer and toolbar
        (activity as MainActivity).setComponentsVisibility(false)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val txtUsername = view.findViewById<EditText>(R.id.txtUsername)
            val txtPassword = view.findViewById<EditText>(R.id.txtPassword)

            Log.d("Username Login", txtUsername.text.toString())
            Log.d("Password Login", txtPassword.text.toString())

            viewModel.userLoginAttempt(txtUsername.text.toString(), txtPassword.text.toString())

            Toast.makeText(view.context, "Success Login", Toast.LENGTH_SHORT).show()


        }

    }

}