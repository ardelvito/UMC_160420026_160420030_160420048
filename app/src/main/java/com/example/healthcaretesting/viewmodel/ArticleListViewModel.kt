package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.model.HealthCareDatabase
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticleListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val articleLiveData = MutableLiveData<List<Article>>()
    val articleLoadErrorLiveData = MutableLiveData<Boolean>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun refresh() {
        loadingLiveData.value = true
        articleLoadErrorLiveData.value = false
        launch {
//                val db = Room.databaseBuilder(
//                    getApplication(),
//                    TodoDatabase::class.java, "newtododb").build()
            val db = buildDB(getApplication())

            articleLiveData.postValue(db.articleDao().selectAllArticles())
        }
    }

    fun clearTask(article: Article) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                HealthCareDatabase::class.java, "healthcaredb").build()
            db.articleDao().deleteArticle(article)

            articleLiveData.postValue(db.articleDao().selectAllArticles())
        }
    }

}