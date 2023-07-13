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
    fun userRegister(vararg user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRegisterAll(articles: List<User>)

    @Query("SELECT * FROM users")
    fun  selectAllUser(): List<User>

    @Query("SELECT * FROM users WHERE uuid= :id")
    fun selectUser(id:Int): User

    @Query("SELECT uuid FROM users WHERE username = :username")
    fun selectUserId(username: String): Int?

    @Query("UPDATE users SET fullname=:fullname, username=:username, phone=:phone, password=:password WHERE uuid = :id")
    fun updateUser(fullname:String, username: String, phone:String, password: String, id:Int)

}

