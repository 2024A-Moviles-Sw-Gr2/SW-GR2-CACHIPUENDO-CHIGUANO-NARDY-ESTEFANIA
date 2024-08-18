package com.example.sw2024agr2proyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class RecipeDetailBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getParcelable(ARG_RECIPE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        // Configurar la vista con los detalles de la receta
        view.findViewById<TextView>(R.id.recipe_title).text = recipe.title
        view.findViewById<TextView>(R.id.recipe_type_time).text = "Desayuno · 60 mins" // Ajustar según los datos de la receta
        view.findViewById<TextView>(R.id.recipe_author_name).text = "Martha Cantúña" // Ajustar según los datos de la receta
        view.findViewById<TextView>(R.id.recipe_rating).text = "4.1/5" // Ajustar según los datos de la receta
        view.findViewById<TextView>(R.id.recipe_description).text = recipe.description
        // Cargar imagen y demás detalles según sea necesario

        return view
    }

    companion object {
        private const val ARG_RECIPE = "recipe"

        fun newInstance(recipe: Recipe): RecipeDetailBottomSheetFragment {
            return RecipeDetailBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RECIPE, recipe)
                }
            }
        }
    }
}