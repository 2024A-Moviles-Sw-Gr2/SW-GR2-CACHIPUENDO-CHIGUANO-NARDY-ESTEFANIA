package com.example.sw2024agr2proyecto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_add -> replaceFragment(Step1Fragment()) // Comienza el proceso de añadir receta
                //R.id.nav_profile -> replaceFragment(ProfileFragment()) // Asúmase que existe
            }
            true
        }
        // Inicia con el HomeFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, HomeFragment())
                .commit()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}