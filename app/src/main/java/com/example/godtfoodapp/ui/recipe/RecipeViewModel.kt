package com.example.godtfoodapp.ui.recipe

import android.arch.lifecycle.MutableLiveData
import com.example.godtfoodapp.base.BaseViewModel
import com.example.godtfoodapp.model.Recipe

class RecipeViewModel : BaseViewModel() {
    private val recipeTitle = MutableLiveData<String>()
    private val recipeIngredients = MutableLiveData<String>()
    private val recipeImageUrl = MutableLiveData<String>()
    private val recipeDescription = MutableLiveData<String>()

    fun bind(recipe: Recipe) {
        recipeTitle.value = recipe.title
        recipeIngredients.value = concatenateIngredientsName(recipe.ingredients)
        recipeImageUrl.value = recipe.images[0].url
        recipeDescription.value = formatDescritionText(recipe.description)
    }

    fun getRecipeTitle(): MutableLiveData<String> {
        return recipeTitle
    }

    fun getRecipeIngredients(): MutableLiveData<String> {
        return recipeIngredients
    }

    fun getRecipeImageUrl(): MutableLiveData<String> {
        return recipeImageUrl
    }

    fun getRecipeDescription(): MutableLiveData<String> {
        return recipeDescription
    }

    fun concatenateIngredientsName(ingredients: List<Recipe.Ingredient>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Ingredients:")
        stringBuilder.append("\n")
        for (ingredient in ingredients) {
            for (element in ingredient.elements) {
                stringBuilder.append(element.name)
                stringBuilder.append(", ")
            }
        }
        return stringBuilder.toString().removeSuffix(", ")
    }

    fun formatDescritionText(description: String): String {
        return description.replace("<br />", "\n", true)
    }
}