package com.tensioner.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.tensioner.application.R
import com.tensioner.application.data_class.Message
import com.tensioner.application.data_class.User

class MessageAdapter(val message: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE =1
    val ITEM_SENT = 2


    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val sentmessage = itemView.findViewById<TextView>(R.id.txt_sent_message)

    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val receivemessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder{
        if(viewType==1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }

    }


    override fun getItemCount(): Int {
        return message.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = message[position]
        if(holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.sentmessage.text = currentMessage.message
        }else{

            val viewHolder = holder as ReceiveViewHolder
            holder.receivemessage.text = currentMessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = message[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }
        else{}
        return ITEM_RECEIVE
    }
}