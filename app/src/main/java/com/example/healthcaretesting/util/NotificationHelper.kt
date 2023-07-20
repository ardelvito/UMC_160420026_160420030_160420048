package com.example.healthcaretesting.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.healthcaretesting.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "todo_channel_id"
    private val NOTIFICATION_ID = 1

    fun createNotification( title:String, message:String) {
//        createNotificationChannel()
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )}

}
