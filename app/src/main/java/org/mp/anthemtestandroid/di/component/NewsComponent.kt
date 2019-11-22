package org.mp.anthemtestandroid.di.component

import dagger.Component
import org.mp.anthemtestandroid.di.module.NewsModule
import org.mp.anthemtestandroid.viewmodel.NewsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface NewsComponent {
    fun injectNewsModule(viewModel: NewsViewModel)
}