package com.example.godtfoodapp.di.component

import com.example.godtfoodapp.di.module.NetworkModule
import com.example.godtfoodapp.ui.detail.RecipeDetailViewModel
import com.example.godtfoodapp.ui.main.RecipeListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(recipeListViewModel: RecipeListViewModel)
    fun inject(recipeDetailViewModel: RecipeDetailViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}