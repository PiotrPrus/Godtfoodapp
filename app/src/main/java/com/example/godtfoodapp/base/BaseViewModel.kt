package com.example.godtfoodapp.base

import android.arch.lifecycle.ViewModel
import com.example.godtfoodapp.di.component.DaggerViewModelInjector
import com.example.godtfoodapp.di.component.ViewModelInjector
import com.example.godtfoodapp.di.module.NetworkModule
import com.example.godtfoodapp.ui.detail.RecipeDetailViewModel
import com.example.godtfoodapp.ui.main.RecipeListViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()


    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is RecipeListViewModel -> injector.inject(this)
            is RecipeDetailViewModel -> injector.inject(this)
        }
    }
}