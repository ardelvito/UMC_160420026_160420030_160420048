package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.model.HealthCareDatabase
import com.example.healthcaretesting.model.Medicine
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MedicineListViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {

    val medicineLiveData = MutableLiveData<List<Medicine>>()
    val medicineLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLiveData.value = true
        medicineLoadErrorLiveData.value = false
        launch {
            val db  = buildDB(getApplication())
            medicineLiveData.postValue(db.medicineDao().selectAllMedicines())
        }

    }

    fun clearTask(medicine: Medicine) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                HealthCareDatabase::class.java, "healthcaredb").build()
            db.medicineDao().deleteMedicine(medicine)

            medicineLiveData.postValue(db.medicineDao().selectAllMedicines())
        }
    }
}