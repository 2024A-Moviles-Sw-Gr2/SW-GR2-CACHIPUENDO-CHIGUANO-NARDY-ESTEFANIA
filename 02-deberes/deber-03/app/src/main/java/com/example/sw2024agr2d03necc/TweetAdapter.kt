package com.example.sw2024agr2d03necc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TweetAdapter(private val tweets: List<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameTextView: TextView = view.findViewById(R.id.tweetUsername)
        val tweetTextView: TextView = view.findViewById(R.id.tweetText)
        val dateTextView: TextView = view.findViewById(R.id.tweetDate)
        val tweetImageView: ImageView = view.findViewById(R.id.tweetImage)
        val likeImageView: ImageView = view.findViewById(R.id.tweetLike)
        val likeCountTextView: TextView = view.findViewById(R.id.tweetLikeCount)
        val retweetImageView: ImageView = view.findViewById(R.id.tweetRetweet)
        val retweetCountTextView: TextView = view.findViewById(R.id.tweetRetweetCount)
        val commentImageView: ImageView = view.findViewById(R.id.tweetComment)
        val commentCountTextView: TextView = view.findViewById(R.id.tweetCommentCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.usernameTextView.text = tweet.username
        holder.tweetTextView.text = tweet.text
        holder.dateTextView.text = tweet.date
        holder.tweetImageView.setImageResource(tweet.imageUrl)
        holder.likeCountTextView.text = tweet.likes.toString()
        holder.retweetCountTextView.text = tweet.retweets.toString()
        holder.commentCountTextView.text = tweet.comments.toString()

        // Add click listeners if needed for likes, retweets, and comments
        holder.likeImageView.setOnClickListener {
            // Handle like action
        }
        holder.retweetImageView.setOnClickListener {
            // Handle retweet action
        }
        holder.commentImageView.setOnClickListener {
            // Handle comment action
        }
    }

    override fun getItemCount(): Int = tweets.size
}
