package com.example.sw2024agr2proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el botón de regresar al HomeFragment
        val go_to_home_button = view.findViewById<Button>(R.id.go_to_home_button)
        go_to_home_button.setOnClickListener {
            // Regresa al HomeFragment o a la pantalla principal
            (activity as RecipeListActivity).replaceFragment(HomeFragment())
        }
    }
}
