package com.example.sw2024agr2d03necc

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        // Returns the number of pages (tabs)
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        // Return the appropriate fragment based on item position
        return when (position) {
            0 -> HomeFragment()      // Home fragment for position 0
            1 -> NotificationsFragment() // Notifications fragment for position 1
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}
