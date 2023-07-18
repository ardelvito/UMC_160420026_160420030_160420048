package com.example.healthcaretesting.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @ColumnInfo(name="url_img")
    var url_img:String,

    @ColumnInfo(name="title")
    var title:String,

    @ColumnInfo(name="author")
    var author:String,

    @ColumnInfo(name="content")
    var content:String,

    @ColumnInfo(name="publish_date")
    var publish_date:String,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
)

@Entity(tableName = "users")
data class User(

    @ColumnInfo(name="fullname")
    var fullname:String? = null,

    @ColumnInfo(name="username")
    var username:String? = null,

    @ColumnInfo(name="phone")
    var phone:String? = null,

    @ColumnInfo(name="gender")
    var gender:String? = null,

    @ColumnInfo(name = "password")
    var password:String? = null,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
)

@Entity(tableName = "doctors")
data class Doctor(

    @ColumnInfo(name="fullname")
    var fullname:String,

    @ColumnInfo(name="speciality")
    var speciality:String,

    @ColumnInfo(name="url_img")
    var url_img:String,

    @ColumnInfo(name="hospital")
    var hospital:String,

    @ColumnInfo(name = "working_days")
    var working_days:String,

    @ColumnInfo(name = "working_hours")
    var working_hours: String,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
){
    val workingDaysList: List<String>
        get() = working_days.split(", ")

    val workingHoursList: List<String>
        get() = working_hours.split(", ")
}

@Entity(tableName = "bookings")
data class Booking(

    @ColumnInfo(name="user_id")
    var user_id:Int,

    @ColumnInfo(name="doctor_id")
    var doctor_id:Int,

    @ColumnInfo(name="disease_complaints")
    var disease_complaints:String,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
)

@Entity(tableName = "facilities")
data class  Facility(
    @ColumnInfo(name= "name")
    var name:String,

    @ColumnInfo(name= "description")
    var description:String,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
)