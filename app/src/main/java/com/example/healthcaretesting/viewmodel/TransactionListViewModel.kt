package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.Booking
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TransactionListViewModel(application: Application): AndroidViewModel(application),CoroutineScope {

    val transactionLD = MutableLiveData<List<Booking>>()
    val transactionErrorLD = MutableLiveData<Boolean>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.IO

    fun refresh(){
        loadingLiveData.value = true
        transactionErrorLD.value = true
        launch {
            val db = buildDB(getApplication())
            transactionLD.postValue(db.bookingDao().selectAllBooking())
        }
    }

}