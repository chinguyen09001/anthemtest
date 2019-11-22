package org.mp.anthemtestandroid

import android.app.Application
import org.mp.anthemtestandroid.di.component.DaggerNewsComponent
import org.mp.anthemtestandroid.di.component.DaggerNewsServiceComponent
/*import org.mp.anthemtestandroid.di.component.DaggerNewsComponent
import org.mp.anthemtestandroid.di.component.DaggerNewsServiceComponent*/
import org.mp.anthemtestandroid.di.component.NewsServiceComponent
import org.mp.anthemtestandroid.di.component.NewsComponent
import org.mp.anthemtestandroid.di.module.NewsModule
import org.mp.anthemtestandroid.di.module.NewsServiceModule

class AnthemNewsApplication : Application() {

    private lateinit var newsComponent: NewsComponent
    private lateinit var newsServiceComponent: NewsServiceComponent

    override fun onCreate() {
        super.onCreate()

        newsComponent = DaggerNewsComponent.builder()
            .newsModule(NewsModule()).build()

        newsServiceComponent = DaggerNewsServiceComponent.builder()
            .newsServiceModule(NewsServiceModule()).build()

    }

    fun getNewsDaoComponent(): NewsComponent{
        return newsComponent
    }

    fun getNewsBaseServiceComponent(): NewsServiceComponent{
        return newsServiceComponent
    }



}