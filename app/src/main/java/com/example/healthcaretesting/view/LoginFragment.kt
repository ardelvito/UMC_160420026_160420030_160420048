package com.example.healthcaretesting.view

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userIdPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SharedPrefs Session & UserID
        sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Hide bottom nav, drawer and toolbar
        (activity as MainActivity).setComponentsVisibility(false)

        //Session Checker
        if (checkSession(sharedPreferences)) {
            // User is already logged in, continue displaying the ArticleListFragment
            Log.d("Status Login", "Anda Sudah Login")
            Toast.makeText(this.context, "Anda Sudah Login!", Toast.LENGTH_LONG).show()

        } else {
            // User is not logged in, navigate to the LoginFragment
            Log.d("Status Login", "Anda Belum Login")
            Toast.makeText(this.context, "Silahkan Login Dahulu", Toast.LENGTH_LONG).show()
        }

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val txtUsername = view.findViewById<EditText>(R.id.txtUsername)
            val txtPassword = view.findViewById<EditText>(R.id.txtPassword)

            Log.d("Username Login", txtUsername.text.toString())
            Log.d("Password Login", txtPassword.text.toString())

            viewModel.userLoginAttempt(txtUsername.text.toString(), txtPassword.text.toString())
            viewModel.getUserId(txtUsername.text.toString())


            if (isLoggedIn()) {
                // Login successful
                Toast.makeText(view.context, "Success Login", Toast.LENGTH_SHORT).show()

                //Direct into home (article list)
                val action = LoginFragmentDirections.actionHome()
                Navigation.findNavController(it).navigate(action)

            } else {
                // Login failed
                Toast.makeText(view.context, "Failed Login", Toast.LENGTH_SHORT).show()

            }


        }

    }

    private fun checkSession(sharedPreferences: SharedPreferences?): Boolean {

        return sharedPreferences?.getBoolean("isLoggedIn", false)!!
    }

    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

}