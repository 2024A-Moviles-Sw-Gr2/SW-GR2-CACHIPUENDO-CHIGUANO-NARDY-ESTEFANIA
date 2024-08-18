package com.example.sw2024agr2proyecto

import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {
    var recipeName: String? = null
    var description: String? = null
    var preparationTime: Int? = null
    var ingredients: MutableList<String> = mutableListOf()
    var steps: MutableList<String> = mutableListOf()

    // Métodos para persistir los datos en la base de datos
    fun saveRecipe() {
        // Aquí va la lógica para guardar la receta en la base de datos
    }
}