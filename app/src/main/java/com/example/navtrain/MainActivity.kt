package com.example.navtrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_dest, R.id.settings_dest), drawerLayout)

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navView.setupWithNavController(navController)

//
//         val toolbar = findViewById<Toolbar>(R.id.toolbar)
//         setSupportActionBar(toolbar)
//
//        val host: NavHostFragment = supportFragmentManager
//            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
//
//        // Set up Action Bar
//        navController = host.navController
//        drawerLayout = findViewById(R.id.drawer_layout)
//
//        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_dest, R.id.settings_dest), drawerLayout)
//        //setupActionBarWithNavController(navController, appBarConfiguration)
//        setupActionBarWithNavController(navController, drawerLayout)
//
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        navigationView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            drawerLayout.openDrawer(Gravity.LEFT)
            // return false;
        }

        // return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//                || super.onSupportNavigateUp()
        return super.onSupportNavigateUp()
    }

}
