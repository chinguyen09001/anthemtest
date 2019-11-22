package org.mp.anthemtestandroid.di.module

import dagger.Module
import dagger.Provides
import org.mp.anthemtestandroid.model.NewsObject
import javax.inject.Singleton

@Module
class NewsModule {

    //News Object is injected here
    @Provides
    @Singleton
    fun provideObject(): NewsObject {
        return NewsObject()
    }

}