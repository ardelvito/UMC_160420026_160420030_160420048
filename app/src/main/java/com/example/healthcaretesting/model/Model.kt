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
    var fullname:String,

    @ColumnInfo(name="username")
    var username:String,

    @ColumnInfo(name="phone")
    var phone:String,

    @ColumnInfo(name="gender")
    var gender:String,

    @ColumnInfo(name = "password")
    var password:String,

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
)