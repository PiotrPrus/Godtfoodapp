package com.example.godtfoodapp.ui.recipe

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.godtfoodapp.R
import com.example.godtfoodapp.databinding.RecipeItemBinding
import com.example.godtfoodapp.model.Recipe

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private lateinit var recipeList: List<Recipe>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecipeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::recipeList.isInitialized) recipeList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun setData(data: List<Recipe>){
        this.recipeList = data
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RecipeViewModel()

        fun bind(recipe: Recipe) {
            viewModel.bind(recipe)
            binding.viewModel = viewModel
        }
    }
}