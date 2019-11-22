package org.mp.anthemtestandroid.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mp.anthemtestandroid.AnthemNewsApplication
import org.mp.anthemtestandroid.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsObject {

    @Inject
    lateinit var newsService: NewsService

    fun getNews(endPoint: String,
                country: String,
                category: String,
                API_KEY: String,
                application: Application): LiveData<News>{

        (application as AnthemNewsApplication).getNewsBaseServiceComponent().injecNewsServiceModule(this)

        val news : MutableLiveData<News> = MutableLiveData()
        val call = newsService.getNews(endPoint, country, category, API_KEY)

        call.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                news.value = null
                Log.i("GETNEWS", t.message.toString())
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                news.value = response.body()
            }
        })

        return news
    }

}