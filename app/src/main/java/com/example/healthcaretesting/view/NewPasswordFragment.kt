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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FragmentNewPasswordBinding
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.viewmodel.NewPasswordViewModel


class NewPasswordFragment : Fragment(), NewPasswordFragmentInterface {

    private lateinit var viewModel: NewPasswordViewModel
    private lateinit var userIdPreferences: SharedPreferences
    private lateinit var dataBinding: FragmentNewPasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentNewPasswordBinding>(inflater, R.layout.fragment_new_password, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_new_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Change text in toolbar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Create New Password"

        userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)

        viewModel = ViewModelProvider(this).get(NewPasswordViewModel::class.java)

//        val btnNewPassword = view.findViewById<Button>(R.id.btnNewPassword)
//        btnNewPassword.setOnClickListener{
//            val txtNewPassword = view.findViewById<EditText>(R.id.txtNewPassword)
//
//            viewModel.updateNewPassword(userId, txtNewPassword.text.toString())
//
//            Toast.makeText(requireContext(), "Success update password", Toast.LENGTH_SHORT).show()
//            val action = NewPasswordFragmentDirections.actionBackToProfile()
//            Navigation.findNavController(it).navigate(action)
//        }

        dataBinding.user = User("", "", "", "", "", 0)
        dataBinding.newPasswordFragmentInterface = this
    }

    override fun onSaveChangePassword(view: View, obj: User) {
        val userId = userIdPreferences.getInt("userId", 0)
//        val newPassword = dataBinding.txtNewPassword.text.toString()
        val newPassword = obj.password.toString()

        viewModel.updateNewPassword(userId, newPassword)
        Toast.makeText(requireContext(), "Success update password", Toast.LENGTH_SHORT).show()
        val action = NewPasswordFragmentDirections.actionBackToProfile()
        Navigation.findNavController(view).navigate(action)
    }

}