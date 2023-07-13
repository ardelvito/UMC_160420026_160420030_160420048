package com.example.healthcaretesting.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val userLiveData = MutableLiveData<User>()

    private val sharedPreferences = getApplication<Application>().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    private val userIdPreferences = getApplication<Application>().getSharedPreferences("UserId", Context.MODE_PRIVATE)

    //Login check
    fun userLoginAttempt(username: String, password: String){
        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()

            val loggedInUser = userDao.userLogin(username, password)

            if(loggedInUser != null){
                Log.d("LOGIN ATTEMPT", "SUCCESS")
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
            }
            else{
                Log.d("LOGIN ATTEMPT", "FAILED")
                sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()

            }
        }
    }

    //Get user id based on the username
    fun getUserId(username: String){
        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()

            val userId = userDao.selectUserId(username)

            if(userId != null){
                Log.d("LOGIN ATTEMPT", "SUCCESS")
                userIdPreferences.edit().putInt("userId", userId).apply()
            }
            else{
                Log.d("LOGIN ATTEMPT", "FAILED")

            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO



}