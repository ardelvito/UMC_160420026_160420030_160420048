package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.healthcaretesting.model.Doctor
import com.example.healthcaretesting.model.HealthCareDatabase
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DoctorListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val doctorLiveData = MutableLiveData<List<Doctor>>()
    val doctorLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLiveData.value = true
        doctorLoadErrorLiveData.value = false
        launch {
            val db = buildDB(getApplication())
            doctorLiveData.postValue(db.doctorDao().selectAllDoctors())
        }
    }

    fun clearTask(doctor: Doctor) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                HealthCareDatabase::class.java, "healthcaredb").build()
            db.doctorDao().deleteDoctor(doctor)

            doctorLiveData.postValue(db.doctorDao().selectAllDoctors())
        }
    }
}