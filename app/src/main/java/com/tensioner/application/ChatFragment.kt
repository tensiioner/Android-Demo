package com.tensioner.application

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tensioner.application.adapters.chatUserAdapter
import com.tensioner.application.data_class.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var chattoolbar: androidx.appcompat.widget.Toolbar
    private lateinit var userchatrecyclerview: RecyclerView
    private lateinit var userAdapter: chatUserAdapter
    private lateinit var userList: ArrayList<User>
    private lateinit var auth: FirebaseAuth
    private lateinit var Dbref: DatabaseReference
    private lateinit var userprogress: ProgressBar
    lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_chat, container, false)
        userchatrecyclerview = view.findViewById(R.id.userchatrecyclerview)
        userprogress = view.findViewById(R.id.userprogressBar)
        chattoolbar = view.findViewById(R.id.chattoolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(chattoolbar)
        setHasOptionsMenu(true)
        menu = chattoolbar.menu
        auth = FirebaseAuth.getInstance()
        Dbref = FirebaseDatabase.getInstance().getReference()
        userList = ArrayList()
        userAdapter = chatUserAdapter(userList)
        userchatrecyclerview.layoutManager = LinearLayoutManager(activity)
        userchatrecyclerview.adapter = userAdapter

        Dbref.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if(auth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }

                }
                userAdapter.notifyDataSetChanged()
                userprogress.visibility = View.GONE
                userchatrecyclerview.visibility = View.VISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chat_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.chatlogout -> {
                Toast.makeText(activity, "logout successful", Toast.LENGTH_LONG).show()
                auth.signOut()
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}