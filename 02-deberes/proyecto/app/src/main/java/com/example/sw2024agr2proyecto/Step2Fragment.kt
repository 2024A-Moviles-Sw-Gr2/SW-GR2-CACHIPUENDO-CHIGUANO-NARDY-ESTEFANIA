package com.example.sw2024agr2proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class Step2Fragment : Fragment() {

    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_step2, container, false)

        // Inicializa el ViewModel
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el botón de siguiente
        val next_button = view.findViewById<Button>(R.id.next_button)
        next_button.setOnClickListener {
            // Guarda los ingredientes y pasos en el ViewModel
            val ingredient_input = view.findViewById<EditText>(R.id.ingredient_input)
            val step_input = view.findViewById<EditText>(R.id.step_input)
            recipeViewModel.ingredients.add(ingredient_input.text.toString())
            recipeViewModel.steps.add(step_input.text.toString())

            // Llamar al método para guardar en la base de datos
            recipeViewModel.saveRecipe()

            // Navega al fragmento de éxito
            (activity as RecipeListActivity).replaceFragment(SuccessFragment())
        }

        // Configura el botón de regresar
        val back_button = view.findViewById<Button>(R.id.back_button)
        back_button.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}