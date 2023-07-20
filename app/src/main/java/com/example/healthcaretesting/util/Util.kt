package com.example.healthcaretesting.util

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.HealthCareDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

val DB_NAME = "healthcaredb"

fun buildDB(context: Context):HealthCareDatabase{
    val db = Room.databaseBuilder(
        context,
        HealthCareDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .addMigrations(MIGRATION_3_4)
        .addMigrations(MIGRATION_4_5)
        .addMigrations(MIGRATION_5_6)
        .build()
    return db
}

//Migration add users table
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table if it exists
        database.execSQL("DROP TABLE IF EXISTS users")

        // Create the new users table
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS users " +
                    "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "fullname TEXT, " +
                    "username TEXT, " +
                    "phone TEXT, " +
                    "gender TEXT, " +
                    "password TEXT)"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table if it exists
        database.execSQL("DROP TABLE IF EXISTS doctors")

        // Create the new users table
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS doctors " +
                    "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "fullname TEXT, " +
                    "speciality TEXT, " +
                    "url_img TEXT, " +
                    "hospital TEXT, " +
                    "working_days TEXT," +
                    "working_hours TEXT)"
        )
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table if it exists
        database.execSQL("DROP TABLE IF EXISTS bookings")

        // Create the new users table
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS bookings " +
                    "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "doctor_id INTEGER, " +
                    "disease_complaints TEXT)"
        )
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table if it exists
        database.execSQL("DROP TABLE IF EXISTS facilities")

        // Create the new users table
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS facilities " +
                    "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "description TEXT)"
        )
    }
}

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE bookings ADD COLUMN schedule STRING")
    }
}


@BindingAdapter("imageUrl","progressBar")
fun loadPhotoUrl(view: ImageView, url: String?, pb:ProgressBar?){
    view.loadImage(url, pb)
}
fun ImageView.loadImage(url: String?, progressBar:ProgressBar?){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                if (progressBar != null) {
                    progressBar.visibility = View.GONE
                }
            }
            override fun onError(e: Exception?) {

            }
        })
}

@BindingAdapter("workingDays")
fun setWorkingDays(textView: TextView, workingDays: String?) {
    workingDays?.let {
        val daysArray = it.split(", ")
        val formattedText = daysArray.joinToString("\n")
        textView.text = formattedText
    }
}

@BindingAdapter("workingHours")
fun setWorkingHours(textView: TextView, workingHours: String?) {
    workingHours?.let {
        val hoursArray = it.split(", ")
        val formattedText = hoursArray.joinToString("\n")
        textView.text = formattedText
    }
}

@BindingAdapter("app:workingDays")
fun setWorkingDays(spinner: Spinner, workingDays: String?) {
    if (!workingDays.isNullOrEmpty()) {
        val workingDaysArray = workingDays.split(", ").toTypedArray()
        val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, workingDaysArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

@BindingAdapter("app:workingHours")
fun setWorkingHours(spinner: Spinner, workingHours: String?) {
    if (!workingHours.isNullOrEmpty()) {
        val workingHoursArray = workingHours.split(", ").toTypedArray()
        val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, workingHoursArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}



