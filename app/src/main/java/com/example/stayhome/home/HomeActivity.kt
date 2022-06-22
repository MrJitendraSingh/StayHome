package com.example.stayhome

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.stayhome.R.id
import com.example.stayhome.databinding.ActivityHomeBinding
import com.example.stayhome.home.dataclass.SharedPreference
import com.example.stayhome.home.dataclass.ViewData
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {
    val t = "HomeActivity"
    lateinit var homeBinding: ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var menu: Menu
    var selectedList : ArrayList<ViewData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        Fresco.initialize(this);
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.red)
        val sharedP = SharedPreference(this)
        setSupportActionBar(homeBinding.toolbar)
        stringText()
        stringReplace()

//        val navController = findNavController(R.id.fragmentContainerView2)
//        homeBinding.toolbar.title = ""
//        appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.order, R.id.account, R.id.setting), homeBinding.homeActivity)

//        setupActionBarWithNavController(navController, appBarConfiguration)
//        homeBinding.navigationDrawer.setupWithNavController(navController)

//        onBackPressed()


//        val drawerLayout: DrawerLayout = homeBinding.homeActivity
        val bottomBar : BottomNavigationView = homeBinding.bottomNavigation
        val navController = findNavController(id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(setOf(id.innerHomeFragment, id.cartFragment, id.historyFragment, id.settingFragment))

        homeBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
        bottomBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { x , destination, y ->
            when(destination.id){
                id.cartFragment -> {
                    homeBinding.toolbar.menu.clear()
                    homeBinding.bottomNavigation.visibility = View.VISIBLE
                }
                id.innerHomeFragment -> {
                    homeBinding.toolbar.inflateMenu(R.menu.top_app_bar)
                    homeBinding.bottomNavigation.visibility = View.VISIBLE
                }
                id.historyFragment -> {
                    homeBinding.toolbar.menu.clear()
                    homeBinding.bottomNavigation.visibility = View.VISIBLE
                }
                id.settingFragment -> {
                    homeBinding.toolbar.menu.clear()
                    homeBinding.bottomNavigation.visibility = View.VISIBLE
                }
                else -> {
                    homeBinding.toolbar.menu.clear()
                    homeBinding.bottomNavigation.visibility = View.GONE
                }
            }
        }

//        navView.setNavigationItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.logOut -> {sharedP.allClear()
//                    Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show()
//                    sharedP.setString("Validation", "one")
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }
//            false
//        }


    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.fragmentContainerView4)
//
//        return NavigationUI.navigateUp(navController, drawerLayout)
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        menuInflater.inflate(R.menu.top_app_bar, menu)
//        val search = menu?.findItem(R.id.search_bar)
//        val searchView = search?.actionView as SearchView
//        searchView.queryHint = "Search Shops"
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                return true
//            }
//
//        })
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (homeBinding.homeActivity.isDrawerOpen(GravityCompat.START)) {
            homeBinding.homeActivity.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            id.notification ->{
                findNavController(id.fragmentContainerView).navigate(id.action_innerHomeFragment_to_notificationFragment)
                return true
            }
            id.search_bar -> {
                findNavController(id.fragmentContainerView).navigate(id.action_innerHomeFragment_to_searchFragment2)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)

//        val search = menu.findItem(R.id.search_bar)
//        val searchView = search.actionView as SearchView
//        searchView.queryHint = "Search"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(searchText: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                val bundle = bundleOf("data" to newText)
//                findNavController(id.fragmentContainerView).navigate(id.searchFragment, bundle)
//                Log.e(t,newText.toString())
//                return true
//            }
//
//        })


        return true
    }

        @SuppressLint("SimpleDateFormat")
        fun stringText(){
           val sendText = "2022-05-10T08:00:00Z"
            val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZZZZZ", Locale.ENGLISH)

            val targetFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm aa")
            val date: Date = originalFormat.parse("2022-01-27T07:54:36+00:00") as Date
            val formattedDate: String = targetFormat.format(date)

//            Log.e("tag------->", formattedDate.toString())
        }

    fun stringReplace(){
        val str = resources.getString(R.string.payment_for_a_free_)
        val x = "xyz"
        val a = "abc"
        val strLine = String.format(str, x, a)
        Log.e("Tag", strLine)
    }
}


