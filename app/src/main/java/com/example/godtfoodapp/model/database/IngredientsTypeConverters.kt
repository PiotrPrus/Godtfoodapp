package com.example.godtfoodapp.model.database

import android.arch.persistence.room.TypeConverter
import com.example.godtfoodapp.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class IngredientsTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun stringToIngredientsList(data: String?): List<Recipe.Ingredient> {

        if(data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Recipe.Ingredient>>()
        {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun ingredientsListToString(ingredientsList: List<Recipe.Ingredient>?): String? {
        if (ingredientsList == null){
            return null
        }
        return gson.toJson(ingredientsList)
    }

    @TypeConverter
    fun stringToElementsList(data: String?): List<Recipe.Ingredient.Element> {
        if(data == null){
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Recipe.Ingredient.Element>>()
        {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun elementsListToString(elementsList: List<Recipe.Ingredient.Element>?): String? {
        if (elementsList == null){
            return null
        }
        return gson.toJson(elementsList)
    }

    @TypeConverter
    fun stringToImagesList(data: String?): List<Recipe.Image>{
        val listType = object : TypeToken<List<Recipe.Image>>()
        {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun imagesToString(imagesList: List<Recipe.Image>?): String? {
        return gson.toJson(imagesList)
    }
}