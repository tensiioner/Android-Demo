package com.tensioner.application

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.media.session.MediaSession
import android.os.Build.ID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_setting_.*
import java.sql.Types.NULL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)


        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_setting_,container, false)
        var tok : View = view.findViewById(R.id.token)
        tok.setOnClickListener{
            var dialog = activity?.let { it1 -> Dialog(it1) }
            dialog!!.setCancelable(true)
            dialog!!.setContentView(R.layout.token_dialogbox)
            var tok:TextView = dialog!!.findViewById(R.id.token5)

            val sharedPreferences: SharedPreferences = (activity?.getSharedPreferences(
              "Token", Context.MODE_PRIVATE) ?: NULL) as SharedPreferences

            val t1 : String? = sharedPreferences.getString("TokenID","")
            tok.text = t1
            dialog?.show()
           // Toast.makeText(activity,"Token",Toast.LENGTH_SHORT).show()
            // var tok:TextView = view.findViewById(R.id.token1)
            //val sharedPreferences: SharedPreferences = (activity?.getSharedPreferences(
             //   "Token", Context.MODE_PRIVATE) ?: NULL) as SharedPreferences

             //val t1 : String? = sharedPreferences.getString("TokenID","")
             //tok.text = t1


        }

        return view


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment setting_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }



}