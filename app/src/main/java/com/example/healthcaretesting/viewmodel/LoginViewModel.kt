package com.example.healthcaretesting.viewmodel

import android.app.Application
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

    fun userLoginAttempt(username: String, password: String){
        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()

            val logedInUser = userDao.userLogin(username, password)

            if(logedInUser != null){
                Log.d("LOGIN ATTEMPT", "SUCCESS")
            }
            else{
                Log.d("LOGIN ATTEMPT", "FAILED")
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO



}