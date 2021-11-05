package com.tensioner.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.lang.UCharacter.BidiPairedBracketType.CLOSE
import android.icu.lang.UCharacter.SentenceBreak.CLOSE
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_after_login.*
import kotlinx.android.synthetic.main.fragment_home_fragment.*

class AfterLogin : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    private val sharedPrefFile = "kotlinsharedpreference"
    private var gview: Boolean = true
    private var lview: Boolean = false
    private lateinit var navView: NavigationView
    //private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)
        //initRecyclerView()
       // addDataSet()

       val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
       val navController = findNavController(R.id.fragmentContainerView)
       // val appBarConfiguration = AppBarConfiguration(setOf(R.id.home_fragment,R.id.setting_Fragment,R.id.profile_fregment))
        //setupActionBarWithNavController(navController,appBarConfiguration)

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







        /*logout.setOnClickListener {


        }*/
    }



    /*private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
           layoutManager = GridLayoutManager(this@AfterLogin,2)
           val topSpacingDecorator = TopSpacingItemDecoration(30)
           addItemDecoration(topSpacingDecorator)
            blogAdapter = BlogRecyclerAdapter()
           adapter = blogAdapter
        }
    }*/

   /*verride fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.item_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.tool_bar) {
            if(gview==true) {

                    recycler_view.layoutManager= GridLayoutManager(this@AfterLogin,2)
                    gview=false
                    lview=true

            }
            else{
                recycler_view.layoutManager = LinearLayoutManager(this@AfterLogin)
                gview=true
                lview=false
            }
        } else{}
        return super.onOptionsItemSelected(item)
    }*/


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