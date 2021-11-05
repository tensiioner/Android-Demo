package com.tensioner.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home_fragment.*

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_after_login.*
import kotlinx.android.synthetic.main.fragment_home_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class home_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null
    private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initRecyclerView()
        addDataSet()

    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }






}