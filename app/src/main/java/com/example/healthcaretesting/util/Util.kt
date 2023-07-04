package com.example.healthcaretesting.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.healthcaretesting.model.HealthCareDatabase

val DB_NAME = "healthcaredb"

fun buildDB(context: Context):HealthCareDatabase{
    val db = Room.databaseBuilder(
        context,
        HealthCareDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
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

