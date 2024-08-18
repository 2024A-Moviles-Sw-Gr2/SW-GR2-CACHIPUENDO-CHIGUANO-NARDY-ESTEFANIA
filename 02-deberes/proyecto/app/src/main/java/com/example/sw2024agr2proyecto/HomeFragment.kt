package com.example.sw2024agr2proyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipes: MutableList<Recipe> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        val searchView = view.findViewById<SearchView>(R.id.searchView)

        // Inicializa el RecyclerView
        recyclerView = view.findViewById(R.id.rvRecipes)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2 columnas en la grid
        recipeAdapter = RecipeAdapter(recipes) { recipe ->
            // Acción al hacer clic en receta
            navigateToRecipeDetail(recipe)
        }
        recyclerView.adapter = recipeAdapter

        // Cargar recetas de prueba
        loadRecipes()

        return view
    }

    private fun loadRecipes() {
        // Aquí puedes cargar recetas de una base de datos o fuente de datos real
        recipes.add(Recipe(1, "Pancakes de Avena", "Descripción breve", "2023-08-20", "image_url", emptyList(), emptyList()))
        recipes.add(Recipe(2, "Humitas", "Descripción breve", "2023-08-21", "image_url", emptyList(), emptyList()))
        recipeAdapter.notifyDataSetChanged()
    }

    private fun navigateToRecipeDetail(recipe: Recipe) {
        // Navegar a RecipeDetailBottomSheetFragment
        val recipeDetailFragment = RecipeDetailBottomSheetFragment.newInstance(recipe)
        recipeDetailFragment.show(parentFragmentManager, recipeDetailFragment.tag)
    }
}