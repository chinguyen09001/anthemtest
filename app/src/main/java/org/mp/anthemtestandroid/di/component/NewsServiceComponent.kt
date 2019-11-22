package org.mp.anthemtestandroid.di.component

import dagger.Component
import org.mp.anthemtestandroid.di.module.NewsServiceModule
import org.mp.anthemtestandroid.model.NewsObject
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsServiceModule::class])
interface NewsServiceComponent {
    fun injecNewsServiceModule(newsObject: NewsObject)
}