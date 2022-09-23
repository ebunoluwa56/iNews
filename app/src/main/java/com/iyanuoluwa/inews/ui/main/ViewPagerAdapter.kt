package com.iyanuoluwa.inews.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iyanuoluwa.inews.data.model.Category

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList: MutableList<Fragment> = ArrayList()

    fun addFragmentToList(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> NewsFragment.newInstance(Category.SPORTS)
            2 -> NewsFragment.newInstance(Category.BUZZ)
            3 -> NewsFragment.newInstance(Category.BUSINESS)
            4 -> NewsFragment.newInstance(Category.TECH)
            else -> throw ArrayIndexOutOfBoundsException()
        }
    }

}