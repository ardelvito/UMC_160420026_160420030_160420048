package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.User
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileDetailViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()
    val userLiveData = MutableLiveData<User>()


    //Update user profile
    fun updateUser(fullname: String, username: String, phone: String, userId: Int) {

        launch {
            val db = buildDB(getApplication())
            val userDao = db.userDao()
            userDao.updateUser(fullname, username, phone, userId)

        }
    }

    //Get user data with specific user id
    fun fetch(uuid: Int){
        launch {
            val db = buildDB(getApplication())
            userLiveData.postValue(db.userDao().selectUser(uuid))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}