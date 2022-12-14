package com.example.cookingrecipesapplication.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipesapplication.adapter.HomeScreenAdapter
import com.example.cookingrecipesapplication.adapter.LoadingStateAdapter
import com.example.cookingrecipesapplication.core.data.Resource
import com.example.cookingrecipesapplication.core.domain.model.Recipes
import com.example.cookingrecipesapplication.databinding.HomeScreenLayoutBinding
import com.example.cookingrecipesapplication.utils.afterMeasure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenView: Fragment() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var binding: HomeScreenLayoutBinding

    private lateinit var adapter: HomeScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeScreenLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeScreenAdapter(::onClickRecipes)

        viewModel.getRecipesByType("main course").observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    setListData(it.data?: listOf())
                }
                is Resource.Error -> {
                    showError(it.message?:"")
                }
            }
        }
    }

    private fun setListData(listData: List<Recipes>){
        adapter.submitList(listData)
        val layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        binding.rvRecipes.layoutManager = layoutManager
        binding.rvRecipes.afterMeasure { startPostponedEnterTransition() }
        binding.rvRecipes.adapter = adapter
    }

    private fun onClickRecipes(recipes: Recipes){
        val action = HomeScreenViewDirections.actionHomeScreenViewToRecipeDetailScreenView(recipes)
        findNavController().navigate(action)
    }

    private fun showLoading(){

    }

    private fun showError(errorMessage: String){
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}