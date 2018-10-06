package com.example.harishkumar.smartshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.harishkumar.smartshop.fragments.ImageListFragment
import java.util.ArrayList

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    var notificationCountCart = 0
     var viewPager:ViewPager ?=null
    var tabLayout:TabLayout ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        viewPager = findViewById<ViewPager>(R.id.viewpager)
        tabLayout = findViewById<TabLayout>(R.id.tabs)
        if (viewPager != null)
        {
            setupViewPager(viewPager!!)
            tabLayout!!.setupWithViewPager(viewPager)
        }


    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        var fragment = ImageListFragment()
        var bundle = Bundle()
        bundle.putInt("type", 1)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_1))
        fragment = ImageListFragment()
        bundle = Bundle()
        bundle.putInt("type", 2)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_2))
        fragment = ImageListFragment()
        bundle = Bundle()
        bundle.putInt("type", 3)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_3))
        fragment = ImageListFragment()
        bundle = Bundle()
        bundle.putInt("type", 4)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_4))
        fragment = ImageListFragment()
        bundle = Bundle()
        bundle.putInt("type", 5)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_5))
        fragment = ImageListFragment()
        bundle = Bundle()
        bundle.putInt("type", 6)
        fragment.arguments = bundle
        adapter.addFragment(fragment, getString(R.string.item_6))
        viewPager.adapter = adapter
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_item1) {
            viewPager!!.currentItem = 0
        } else if (id == R.id.nav_item2) {
            viewPager!!.currentItem = 1
        } else if (id == R.id.nav_item3) {
            viewPager!!.currentItem = 2
        } else if (id == R.id.nav_item4) {
            viewPager!!.currentItem = 3
        } else if (id == R.id.nav_item5) {
            viewPager!!.currentItem = 4
        } else if (id == R.id.nav_item6) {
            viewPager!!.currentItem = 5
        } else if (id == R.id.my_wishlist) {
//            startActivity(Intent(this@MainActivity, WishlistActivity::class.java))
        } else if (id == R.id.my_cart) {
//            startActivity(Intent(this@MainActivity, CartListActivity::class.java))
        } else {
//            startActivity(Intent(this@MainActivity, EmptyActivity::class.java))
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        val item = menu.findItem(R.id.action_cart)
//        NotificationCountSetClass.setAddToCart(this@MainActivity, item, notificationCountCart)
        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_search) {
//            startActivity(Intent(this@MainActivity, SearchResultActivity::class.java))
            return true
        } else if (id == R.id.action_cart) {

            /* NotificationCountSetClass.setAddToCart(MainActivity.this, item, notificationCount);
            invalidateOptionsMenu();*/
//            startActivity(Intent(this@MainActivity, CartListActivity::class.java))

            /* notificationCount=0;//clear notification count
            invalidateOptionsMenu();*/
            return true
        } else {
//            startActivity(Intent(this@MainActivity, EmptyActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
