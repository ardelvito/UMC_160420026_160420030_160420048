package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookingConfirmationPasswordViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {

    private val job = Job()
    private val _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword: LiveData<Boolean> get() = _isValidPassword

    fun confirmUserPassword(userId: Int, password: String) {

        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()
            val userOldPassword = userDao.getUserPassword(userId)
            val isValid = userOldPassword == password
            _isValidPassword.postValue(isValid)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}