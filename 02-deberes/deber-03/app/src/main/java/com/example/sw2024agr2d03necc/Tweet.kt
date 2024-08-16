package com.example.sw2024agr2d03necc

data class Tweet(
    val username: String,
    val text: String,
    val date: String,
    val imageUrl: Int, // Resource ID for local images or URL as String for web images
    val likes: Int,
    val retweets: Int,
    val comments: Int // Adding comments count
)
