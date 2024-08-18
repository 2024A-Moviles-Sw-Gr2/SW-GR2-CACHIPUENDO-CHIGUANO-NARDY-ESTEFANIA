package com.example.sw2024agr2proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sw2024agr2proyecto.R

class RecipeAdapter(
    private val recipes: MutableList<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        private val recipeTitle: TextView = itemView.findViewById(R.id.recipe_title)
        private val recipeRating: TextView = itemView.findViewById(R.id.recipe_rating)
        private val recipeType: TextView = itemView.findViewById(R.id.recipe_type)
        private val recipeTime: TextView = itemView.findViewById(R.id.recipe_time)
        private val authorImage: ImageView = itemView.findViewById(R.id.autor_image)
        private val authorName: TextView = itemView.findViewById(R.id.autor_name)

        fun bind(recipe: Recipe) {
            // Aquí puedes cargar la imagen con una librería como Glide o Picasso
            // Glide.with(itemView.context).load(recipe.image).into(recipeImage)
            recipeTitle.text = recipe.title
            recipeRating.text = "3.5/5" // Por ahora es estático, puedes hacerlo dinámico
            recipeType.text = "Desayuno" // Por ahora es estático, puedes hacerlo dinámico
            recipeTime.text = "60 min" // Por ahora es estático, puedes hacerlo dinámico
            authorName.text = "Kevin López" // Por ahora es estático, puedes hacerlo dinámico

            itemView.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }
}