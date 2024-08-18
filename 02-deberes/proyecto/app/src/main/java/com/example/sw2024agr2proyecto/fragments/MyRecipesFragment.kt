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

class MyRecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipes: MutableList<Recipe> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_recipes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recipeAdapter = RecipeAdapter(recipes) { recipe ->
            // Acción al hacer clic en receta (opcional)
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
}