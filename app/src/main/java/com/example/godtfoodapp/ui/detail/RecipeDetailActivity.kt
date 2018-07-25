package com.example.godtfoodapp.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.godtfoodapp.R
import com.example.godtfoodapp.databinding.ActivityRecipeDetailBinding
import kotlinx.android.synthetic.main.activity_recipe_detail.*

class RecipeDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail)
        viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel::class.java)
        binding.viewModel = viewModel
        setSupportActionBar(recipe_detail_toolbar)
    }
}