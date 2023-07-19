package com.example.healthcaretesting.view

import android.view.View
import com.example.healthcaretesting.model.Doctor
import com.example.healthcaretesting.model.User

interface LoginFragmentInterface{
    fun onLoginClick(view: View, obj: User)
    fun onRegisterClick(view: View)
}

interface RegisterFragmentInterface{
    fun onRadioGenderClick(view: View, gender: Int, obj: User)
    fun onUserRegisterClick(view: View, obj: User)
}

interface MyProfileFragmentInterface{
    fun onLogoutClick(view: View)
    fun onEditClick(view: View)
}

interface MyProfileDetailFragmentInterface{
    fun onChangePasswordClick(view: View)
    fun onSaveEditProfileClick(view: View, obj: User)
}

interface ConfirmationPasswordFragmentInterface{
    fun onConfirmChangePassword(view: View, obj: User)
}

interface NewPasswordFragmentInterface{
    fun onSaveChangePassword(view: View, obj: User)
}

interface DoctorListFragmentInterface{
    fun onDoctorDetailClick(view: View)
}

interface DoctorDetailFragmentInterface{
    fun onBookingSchedule(view: View)
}

interface BookingDoctorFragmentInterface{
    fun onBookingNow(view: View, obj: Doctor)

}

interface ArticleListFragmentInterface{
    fun onDetailClick(view: View)

}

interface FasilitasListFragmentInterface{
    fun onDetailClick(view: View)

}