package com.auf.cea.pyalungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.auf.cea.pyalungan.databinding.ActivityMainBinding
import com.auf.cea.pyalungan.fragments.UserDetailsFragment

class MainActivity : AppCompatActivity(), UserDetailsFragment.UserDetailsInterface {
    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // INITIALIZE NAV DRAWER
        setSupportActionBar(binding.appBarContainer.toolbar)
        // Call drawer Layout
        val drawerLayout : DrawerLayout = binding.drawerLayout
        val navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_container)

        // APP BAR CONFIG
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_user_details,R.id.nav_rpc_game, R.id.nav_toss_coin_game, R.id.nav_dice_roller_game
            ), drawerLayout
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

    }

//    override fun onResume() {
//        super.onResume()
//        binding.drawerLayout.openDrawer(GravityCompat.START)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onEdit(username: String) {
        Log.d(MainActivity::class.java.simpleName, username)
    }
}