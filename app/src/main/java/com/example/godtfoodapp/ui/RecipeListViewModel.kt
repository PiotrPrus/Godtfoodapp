package com.example.godtfoodapp.ui

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.example.godtfoodapp.R
import com.example.godtfoodapp.base.BaseViewModel
import com.example.godtfoodapp.model.Recipe
import com.example.godtfoodapp.model.RecipeDao
import com.example.godtfoodapp.network.GodtApi
import com.example.godtfoodapp.ui.recipe.RecipeListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeListViewModel(private val recipeDao: RecipeDao) : BaseViewModel() {

    @Inject
    lateinit var godtApi: GodtApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRecipes() }
    val recipeListAdapter = RecipeListAdapter()

    private lateinit var subscription: Disposable

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        subscription = Observable.fromCallable { recipeDao.all }
                .concatMap { dbRecipeList ->
                    if (dbRecipeList.isEmpty())
                        godtApi.getRecipes().concatMap { apiRecipeList ->
                            recipeDao.insertAll(*apiRecipeList.toTypedArray())
                            Observable.just(apiRecipeList)
                        } else {
                        Observable.just(dbRecipeList)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onFetchRecipeListStart() }
                .doOnTerminate { onFetchRecipeListFinish() }
                .subscribe(
                        { result -> onFetchRecipeListSuccess(result) },
                        { error -> onFetchRecipeListError(error) })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onFetchRecipeListError(error: Throwable) {
        errorMessage.value = R.string.fetch_recipe_error
        Log.d("Network", "Error message: "+ error.message)
    }

    private fun onFetchRecipeListSuccess(result: List<Recipe>) {
        recipeListAdapter.setData(result)
    }

    private fun onFetchRecipeListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onFetchRecipeListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }
}