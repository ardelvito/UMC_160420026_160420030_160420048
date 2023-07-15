package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewPasswordViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    //Update new password
    fun updateNewPassword(userId: Int, newPassword: String){

        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()
            userDao.updatePassword(newPassword, userId)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}