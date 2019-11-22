package org.mp.anthemtestandroid.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import org.mp.anthemtestandroid.AnthemNewsApplication
import org.mp.anthemtestandroid.network.NewsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


// Retrofit and news service network call
@Module
class NewsServiceModule {

    //I actually mistyped the URL and got scared when I got the blank screen
    private var BASE_URL : String = "https://newsapi.org"
    private var retrofitBuilder: Retrofit.Builder

    init{

        retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Singleton
    fun createService():NewsService {
        val retrofit = retrofitBuilder.build()
        return retrofit.create(NewsService::class.java)
    }

}