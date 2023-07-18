package com.example.healthcaretesting.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface HealthCareDao {
}

@Dao
interface ArticleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Insert
    fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun  selectAllArticles(): List<Article>

    @Query("SELECT * FROM articles WHERE uuid= :id")
    fun selectArticle(id:Int): Article

    @Delete
    fun deleteArticle(article: Article)
}

@Dao
interface UserDao{
    @Query("SELECT * FROM users WHERE username =:username AND password = :password")
    fun userLogin(username:String, password:String):User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRegister(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRegisterAll(articles: List<User>)

    @Query("SELECT * FROM users")
    fun  selectAllUser(): List<User>

    @Query("SELECT * FROM users WHERE uuid= :id")
    fun selectUser(id:Int): User

    @Query("SELECT uuid FROM users WHERE username = :username")
    fun selectUserId(username: String): Int?

    @Query("UPDATE users SET fullname=:fullname, username=:username, phone=:phone WHERE uuid = :id")
    fun updateUser(fullname:String, username: String, phone:String, id:Int)

    @Query("SELECT password FROM users WHERE uuid = :userId")
    fun getUserPassword(userId: Int): String?

    @Query("UPDATE users SET password=:password WHERE uuid=:id")
    fun updatePassword(password: String, id: Int)
}

@Dao
interface DoctorDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doctor: List<Doctor>)

    @Query("SELECT * FROM doctors")
    fun  selectAllDoctors(): List<Doctor>

    @Query("SELECT * FROM doctors WHERE uuid= :id")
    fun selectDoctor(id:Int): Doctor

    @Query("SELECT * FROM doctors WHERE uuid= :id")
    fun selectSchedule(id: Int): Doctor

    @Delete
    fun deleteDoctor(doctor: Doctor)

}

@Dao
interface BookingDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(booking: List<Booking>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(booking: Booking): Long

    @Query("SELECT * FROM bookings")
    fun  selectAllBooking(): List<Booking>

    @Query("SELECT * FROM bookings WHERE doctor_id= :doctor_id")
    fun  selectBookingByDoctor(doctor_id: Int): List<Booking>

    @Query("SELECT * FROM bookings WHERE user_id= :user_id")
    fun  selectBookingByUser(user_id: Int): List<Booking>
    @Delete
    fun deleteBooking(booking: Booking)

}

@Dao
interface FacilityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facility: List<Facility>)

    @Query("SELECT * FROM facilities")
    fun  selectAllFacility(): List<Facility>

    @Query("SELECT * FROM facilities WHERE uuid= :id")
    fun  selectFacility(id: Int): Facility

}


