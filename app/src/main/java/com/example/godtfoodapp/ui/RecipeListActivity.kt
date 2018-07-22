package com.example.godtfoodapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.godtfoodapp.R
import com.example.godtfoodapp.databinding.ActivityRecipeListBinding
import com.example.godtfoodapp.di.ViewModelFactory

class RecipeListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var viewModel: RecipeListViewModel
    private var errorSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list)
        binding.listRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(RecipeListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            when(errorMessage) {
                null -> hideError()
                else -> showError(errorMessage)
            }
        })
        binding.viewModel = viewModel
    }

    private fun showError(errorMessage: Int) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.generic_retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }
}