package com.example.sw2024agr2d03necc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewNotifications) // Ensure this ID matches your RecyclerView ID in the fragment layout
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NotificationAdapter(getNotifications())
        recyclerView.adapter = adapter
        return view
    }

    private fun getNotifications(): List<Notification> {
        // This should fetch real notifications data
        return listOf(
            Notification(R.drawable.default_user, "Luisa", "Luisa esta en directo"),
            Notification(R.drawable.default_user, "Karime", "Wey como que no me quiere contestar"),
            Notification(R.drawable.default_user, "Ray", "@Karime te voy a mandar un mensaje"),
        )
    }
}
