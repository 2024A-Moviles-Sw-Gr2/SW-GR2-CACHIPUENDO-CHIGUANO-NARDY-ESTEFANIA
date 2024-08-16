package com.example.sw2024agr2d03necc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(private val notifications: List<Notification>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.notificationIcon)
        val titleTextView: TextView = view.findViewById(R.id.notificationTitle)
        val contentTextView: TextView = view.findViewById(R.id.notificationContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        holder.iconImageView.setImageResource(notification.icon)
        holder.titleTextView.text = notification.title
        holder.contentTextView.text = notification.content
    }

    override fun getItemCount() = notifications.size
}
