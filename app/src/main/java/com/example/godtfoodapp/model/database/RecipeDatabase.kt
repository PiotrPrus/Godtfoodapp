package com.example.godtfoodapp.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.godtfoodapp.model.Recipe
import com.example.godtfoodapp.model.RecipeDao

@Database(entities = [(Recipe::class)], version = 1)
@TypeConverters(IngredientsTypeConverters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}