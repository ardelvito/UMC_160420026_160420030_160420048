package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.Booking
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookingReportViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()

    fun insertBooking(booking: Booking): LiveData<Boolean> {
        val bookingResult = MutableLiveData<Boolean>()

        launch {
            val db = buildDB(getApplication())
            val result = db.bookingDao().insert(booking)
            bookingResult.postValue(result>0)
        }
        return bookingResult
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}