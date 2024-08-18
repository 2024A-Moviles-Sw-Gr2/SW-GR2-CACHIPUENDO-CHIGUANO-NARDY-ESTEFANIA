    package com.example.sw2024agr2proyecto

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider

    class Step1Fragment : Fragment() {

        private lateinit var recipeViewModel: RecipeViewModel

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_step1, container, false)

            // Inicializa el ViewModel
            recipeViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Configura el botón de siguiente
            val next_button = view.findViewById<Button>(R.id.next_button)
            next_button.setOnClickListener {
                // Guarda los datos en el ViewModel
                val recipe_name_input = view.findViewById<EditText>(R.id.recipe_name_input)
                val recipe_description_input = view.findViewById<EditText>(R.id.recipe_description_input)
                val preparation_time_slider = view.findViewById<Slider>(R.id.preparation_time_slider)
                recipeViewModel.recipeName = recipe_name_input.text.toString()
                recipeViewModel.description = recipe_description_input.text.toString()
                recipeViewModel.preparationTime = preparation_time_slider.value.toInt()

                // Navega al siguiente fragmento
                (activity as RecipeListActivity).replaceFragment(Step2Fragment())
            }
        }
    }