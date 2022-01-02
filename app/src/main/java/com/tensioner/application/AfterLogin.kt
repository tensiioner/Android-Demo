package com.tensioner.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_after_login.*

class AfterLogin : AppCompatActivity() {
    private lateinit var toggle : ActionBarDrawerToggle
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

       val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        val navController = findNavController(R.id.productFragment)
        bottomNavigationView.setupWithNavController(navController)


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView = findViewById(R.id.nav_top)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.homem -> {Toast.makeText(applicationContext,"Home",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.message->{Toast.makeText(applicationContext,"Message",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.sync -> {Toast.makeText(applicationContext,"Sync in Progress",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.trash -> {Toast.makeText(applicationContext,"Deleting",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.setting -> {Toast.makeText(applicationContext,"Open Setting",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.loginm ->{Toast.makeText(applicationContext,"Please Login",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.logoutm -> {
                    Toast.makeText(applicationContext, "Logout Successful", Toast.LENGTH_SHORT)
                        .show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                        sharedPrefFile,
                        Context.MODE_PRIVATE
                    )
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("checkBox", "false")
                    editor.apply()
                    Toast.makeText(this, "Logout successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.share -> {Toast.makeText(applicationContext,"Share",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
                R.id.rateus -> {Toast.makeText(applicationContext,"Rate us",Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)}
            }
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}