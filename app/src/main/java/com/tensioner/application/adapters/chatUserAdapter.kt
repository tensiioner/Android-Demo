package com.tensioner.application.adapters

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.google.firebase.auth.FirebaseAuth
import com.tensioner.application.*
import com.tensioner.application.data_class.User

class chatUserAdapter(val user: List<User>) : RecyclerView.Adapter<chatUserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var username = itemView.findViewById<TextView>(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.userlistlayout,parent,false)
        return chatUserAdapter.UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = user[position]
        holder.username.text=user.name

        holder.itemView.setOnClickListener{
          val intent = Intent(getApplicationContext(), MessageActivity::class.java)
            intent.putExtra("name",user.name)
            intent.putExtra("uid", user.uid)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            getApplicationContext().startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return user.size
    }
}