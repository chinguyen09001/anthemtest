package org.mp.anthemtestandroid.network

import org.mp.anthemtestandroid.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsService {

    @GET
    fun getNews(
        @Url endPoint: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") API_KEY: String): Call<News>

}