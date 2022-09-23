package com.iyanuoluwa.inews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.iyanuoluwa.inews.data.model.Category
import com.iyanuoluwa.inews.ui.main.HomeFragment
import com.iyanuoluwa.inews.ui.main.NewsFragment
import com.iyanuoluwa.inews.ui.main.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        tabLayout = findViewById(R.id.tabLayout)

        val sportsFragment = NewsFragment.newInstance(Category.SPORTS)
        val buzzFragment = NewsFragment.newInstance(Category.BUZZ)
        val businessFragment = NewsFragment.newInstance(Category.BUSINESS)
        val techFragment = NewsFragment.newInstance(Category.TECH)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragmentToList(HomeFragment())
        viewPagerAdapter.addFragmentToList(sportsFragment)
        viewPagerAdapter.addFragmentToList(buzzFragment)
        viewPagerAdapter.addFragmentToList(techFragment)
        viewPagerAdapter.addFragmentToList(businessFragment)

        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = 4

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.isDrawerSlideAnimationEnabled = true
        toggle?.syncState()

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout?.selectTab(tabLayout?.getTabAt(position))
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}