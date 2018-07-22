package com.example.godtfoodapp.network

import com.example.godtfoodapp.model.Recipe
import com.example.godtfoodapp.utils.FIXED_GODT_ENDPOINT
import io.reactivex.Observable
import retrofit2.http.GET

interface GodtApi {

    @GET(FIXED_GODT_ENDPOINT)
    fun getRecipes(): Observable<List<Recipe>>

}