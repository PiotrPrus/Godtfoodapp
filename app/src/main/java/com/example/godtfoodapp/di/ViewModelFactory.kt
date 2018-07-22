package com.example.godtfoodapp.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.example.godtfoodapp.model.database.RecipeDatabase
import com.example.godtfoodapp.ui.RecipeListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            val database = Room.databaseBuilder(activity.applicationContext, RecipeDatabase::class.java, "recipes").build()
            @Suppress("UNCHECKED_CAST")
            return RecipeListViewModel(database.recipeDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}