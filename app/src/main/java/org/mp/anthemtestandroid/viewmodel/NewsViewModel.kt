package org.mp.anthemtestandroid.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.mp.anthemtestandroid.AnthemNewsApplication
import org.mp.anthemtestandroid.model.Article
import org.mp.anthemtestandroid.model.News
import org.mp.anthemtestandroid.model.NewsObject
import javax.inject.Inject

class NewsViewModel: ViewModel() {

    @Inject
    lateinit var newsObject: NewsObject

    lateinit var newsLiveData: LiveData<News>


    fun getNews(endPoint: String, country: String, caterogy: String, API_KEY: String, application: Application): LiveData<News>{
        (application as AnthemNewsApplication).getNewsDaoComponent().injectNewsModule(this)
        newsLiveData = newsObject.getNews(endPoint, country, caterogy, API_KEY, application)
        return newsLiveData
    }

    fun getArticleWebUrl(position: Int?): String? {
        return if (newsLiveData.value?.articles?.size!! > position!!)

            newsLiveData.value?.articles!![position].url
        else
            null

    }

    fun getArticle(position: Int?): Article? {
        return when{
            newsLiveData.value?.articles == null -> null
            newsLiveData.value?.articles?.size!! > position!! -> newsLiveData.value?.articles!![position]
            else -> null
        }
    }



}