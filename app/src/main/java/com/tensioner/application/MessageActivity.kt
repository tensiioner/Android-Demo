package com.tensioner.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tensioner.application.adapters.MessageAdapter
import com.tensioner.application.data_class.Message
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {
    var receiverRoom:String? = null
    var senderRoom:String? = null
    private lateinit var Dbref : DatabaseReference
    private lateinit var message1: EditText
    private lateinit var messagesend1 : ImageView
    private lateinit var chatrecyclerview1 : RecyclerView
    private lateinit var messageList : ArrayList<Message>
    private lateinit var messageAdapater: MessageAdapter
    private lateinit var chattoolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val name = intent.getStringExtra("name")
        val uid =  intent.getStringExtra("uid")
        supportActionBar?.title = name
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        Dbref = FirebaseDatabase.getInstance().getReference()
        senderRoom = uid + senderUid
        receiverRoom = senderUid + uid
        chatrecyclerview1 = findViewById(R.id.chatrecyclerview)
        chattoolbar = findViewById(R.id.messagetoolbar)
        setSupportActionBar(chattoolbar)
        chattoolbar.title = name
        message1 = findViewById(R.id.messagebox)
        messagesend1 = findViewById(R.id.messagesend)
        messageList = ArrayList()


        messageAdapater= MessageAdapter(messageList)

        chatrecyclerview1.layoutManager = LinearLayoutManager(this)
        chatrecyclerview1.adapter = messageAdapater
        Dbref.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                   messageAdapater.notifyDataSetChanged()
                    chatrecyclerview1.scrollToPosition(messageList.size -1)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })



        messagesend1.setOnClickListener{
            val message = message1.text.toString()
            val messageObject = Message(message,senderUid)

            Dbref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    Dbref.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            messagebox.setText("")
        }


    }
}