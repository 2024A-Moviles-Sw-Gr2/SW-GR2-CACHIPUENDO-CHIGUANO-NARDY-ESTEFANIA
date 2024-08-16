package com.example.sw2024agr2d03necc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tweetAdapter: TweetAdapter
    private val tweets = listOf(
        // Updated Tweet initializations with new parameters: likes, retweets, and comments
        Tweet("Luis Peralta", "Day 1 of SQL\n" +
                "\n" +
                "- Introduction to Databases and SQL \n" +
                "- Basic Sql syntax \n" +
                "- Connecting to a sample database", "Jan 1, 2024", R.drawable.tweet1, 10, 5, 3),
        Tweet("DevTalles", "Nuevo curso de ASTRO", "Jan 2, 2024", R.drawable.tweet2, 20, 10, 6),
        Tweet("AstroGames", "No te pierdas la nueva actualización", "Jan 3, 2024", R.drawable.tweet3, 30, 15, 9),
        Tweet("JamesDev", "Todos los IDEs que debes conocer", "Jan 4, 2024", R.drawable.tweet4, 40, 20, 12)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        tweetAdapter = TweetAdapter(tweets)
        recyclerView.adapter = tweetAdapter
        return view
    }
}

