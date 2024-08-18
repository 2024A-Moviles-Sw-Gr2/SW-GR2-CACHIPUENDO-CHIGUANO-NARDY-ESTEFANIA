package com.example.sw2024agr2proyecto.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sw2024agr2proyecto.R
import com.example.sw2024agr2proyecto.Recipe
import com.example.sw2024agr2proyecto.RecipeAdapter

class SavedRecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val savedRecipes: MutableList<Recipe> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved_recipes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recipeAdapter = RecipeAdapter(savedRecipes) { recipe ->
            // Acción al hacer clic en receta (opcional)
        }
        recyclerView.adapter = recipeAdapter

        // Cargar recetas de prueba
        loadSavedRecipes()

        return view
    }

    private fun loadSavedRecipes() {
        // Aquí puedes cargar recetas guardadas de una base de datos o fuente de datos real
        savedRecipes.add(Recipe(3, "Pancakes", "Descripción breve", "2023-08-20", "image_url", emptyList(), emptyList()))
        savedRecipes.add(Recipe(4, "Humitas", "Descripción breve", "2023-08-21", "image_url", emptyList(), emptyList()))
        recipeAdapter.notifyDataSetChanged()
    }
}