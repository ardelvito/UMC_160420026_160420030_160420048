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
        .addMigrations(MIGRATION_2_3)
        .addMigrations(MIGRATION_3_4)
        .addMigrations(MIGRATION_4_5)
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
