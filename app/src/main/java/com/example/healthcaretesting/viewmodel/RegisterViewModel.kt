package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLiveData = MutableLiveData<User>()

    //User register
    fun userRegister(user: User): LiveData<Boolean> {
        val registrationResult = MutableLiveData<Boolean>()
        launch {
            val db = buildDB(getApplication())
            val result = db.userDao().userRegister(user)
            registrationResult.postValue( result > 0)
        }
        return registrationResult
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}