package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.Facility
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FacilityListViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {

    val facilityLiveData = MutableLiveData<List<Facility>>()
    val facilityLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLiveData.value = true
        facilityLoadErrorLiveData.value = false
        launch {
            val db  = buildDB(getApplication())
            facilityLiveData.postValue(db.facilityDao().selectAllFacility())
        }

    }

//    fun clearTask(facility: Facility) {
//        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                HealthCareDatabase::class.java, "healthcaredb").build()
//            db.facilityDao().deleteFacility(facility)
//
//            facilityLiveData.postValue(db.facilityDao().selectAllfacilitys())
//        }
//    }
}