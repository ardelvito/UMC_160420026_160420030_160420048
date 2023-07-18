package com.example.healthcaretesting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticleDetailViewModel(application: Application): AndroidViewModel(application),
    CoroutineScope {

    val articleLD =MutableLiveData<Article>()
    val articleLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private var job = Job()

     fun detail(id:Int){
        loadingLiveData.value = true
        articleLoadErrorLiveData.value = false
        launch{
            val db = buildDB(getApplication())
            articleLD.postValue(db.articleDao().selectArticle(id))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}