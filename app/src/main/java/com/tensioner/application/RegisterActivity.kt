package com.tensioner.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.tensioner.application.data_class.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        auth = Firebase.auth
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )

        register.setOnClickListener {

            singup(inputUserNameRegister.text.toString(),inputEmailRegister.text.toString(), inputPasswordRegister.text.toString())

        }
        /*val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            if(((inputUserNameRegister.text.toString()) != "") && ((inputEmailRegister.text.toString()) != "") && ((inputPasswordRegister.text.toString()) != "")) {
                editor.putString("user_name", inputUserNameRegister.text.toString())
                editor.putString("email_id", inputEmailRegister.text.toString())
                editor.putString("password", inputPasswordRegister.text.toString())
                editor.apply()
                editor.commit()
                Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Field Can't be empty", Toast.LENGTH_LONG).show()
            }
        }*/
        loginpage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun singup(name:String,email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        addUserToDatabase(name,email,auth.currentUser?.uid!!)
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Error in registration please try again", Toast.LENGTH_LONG).show()

                }
            }


    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
         dbRef = FirebaseDatabase.getInstance().getReference()
        dbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}