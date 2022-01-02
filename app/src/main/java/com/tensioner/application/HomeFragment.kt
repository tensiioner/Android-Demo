package com.tensioner.application


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tensioner.application.HomeFragment.Companion.newInstance
import kotlinx.android.synthetic.main.fragment_home_fragment.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_after_login.*

class HomeFragment : Fragment() {

    private lateinit var blogAdapter: BlogRecyclerAdapter
    private lateinit var recView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initRecyclerView()
       // addDataSet()
    }


   /* private fun addDataSet(){

    }

    private fun initRecyclerView() {

    }
    */


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(com.tensioner.application.R.layout.fragment_home_fragment, container, false)
        recView = view.findViewById(R.id.recycler_view)
        recView.apply {
            layoutManager = GridLayoutManager(activity,2)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            blogAdapter = BlogRecyclerAdapter()
            adapter = blogAdapter
            blogAdapter.setOnItemClickListener(object : BlogRecyclerAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    if(position==1)
                    {  //val intent = Intent(activity,Pdesc::class.java)
                        //startActivity(intent)
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.productFragment,AfterProductPage.newInstance("A","B")).addToBackStack(null)
                            .commit()
                    }

                }

            })
        }
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}
