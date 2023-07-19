package com.example.healthcaretesting.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthcaretesting.util.MIGRATION_1_2
import com.example.healthcaretesting.util.MIGRATION_2_3
import com.example.healthcaretesting.util.MIGRATION_3_4
import com.example.healthcaretesting.util.MIGRATION_4_5
import com.example.healthcaretesting.util.MIGRATION_5_6

@Database(entities = [Article::class, User::class, Doctor::class, Booking::class, Facility::class], version = 4)
abstract class HealthCareDatabase: RoomDatabase(){
    abstract fun articleDao(): ArticleDao
    abstract fun userDao(): UserDao
    abstract fun doctorDao(): DoctorDao
    abstract fun bookingDao(): BookingDao
    abstract fun facilityDao(): FacilityDao




    companion object {

        @Volatile
        private var instance: HealthCareDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): HealthCareDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }



        private fun buildDatabase(context: Context): HealthCareDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                HealthCareDatabase::class.java,
                "healthcaredb"
            )
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_3)
                .addMigrations(MIGRATION_3_4)
                .addMigrations(MIGRATION_4_5)
                .build()

        }
    }

}
