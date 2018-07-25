package com.example.godtfoodapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.godtfoodapp.R
import com.example.godtfoodapp.databinding.ActivityRecipeListBinding
import com.example.godtfoodapp.di.ViewModelFactory
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_recipe_list.*


class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var viewModel: RecipeListViewModel
    private var errorSnackBar: Snackbar? = null
    private lateinit var filterDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list)
        binding.listRecyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(RecipeListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            when (errorMessage) {
                null -> hideError()
                else -> showError(errorMessage)
            }
        })
        binding.viewModel = viewModel
        setSupportActionBar(recipe_list_toolbar)
    }

    override fun onDestroy() {
        filterDisposable.dispose()
        super.onDestroy()
    }

    private fun showError(errorMessage: Int) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.generic_retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        initSearchMenuItem(menu)
        return true
    }

    private fun initSearchMenuItem(menu: Menu?) {
        val searchItem: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = getString(R.string.search_hint)
        filterDisposable = RxSearchView.queryTextChanges(searchView)
                .skipInitialValue().subscribe { query -> viewModel.filterList(query.toString()) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search -> {
                onSearchRequested()
                true
            }
            else -> false

        }
    }
}