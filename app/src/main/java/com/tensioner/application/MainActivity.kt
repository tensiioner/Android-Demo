package com.tensioner.application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tensioner.application.Interface.LoginResultCallBacks
import com.tensioner.application.databinding.ActivityMainBinding
import com.tensioner.application.viewModel.LoginViewModelFactory
import com.tensioner.application.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(),LoginResultCallBacks{
    private lateinit var callbackManager: CallbackManager
    private val Email = "email"
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
       activityMainBinding.viewModel = ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)
        val button:Button = findViewById(R.id.login_button)
        Login.setOnClickListener {
            login(inputEmail.text.toString(),inputPassword.text.toString())
        }
        login_button.setOnClickListener{
            login_button.setPermissions(listOf("email","id","name"))
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }

                override fun onSuccess(result: LoginResult) {
                    Log.d("FACEBOOKDATA","name")
                    val graphRequest = GraphRequest.newMeRequest(result.accessToken){ obj, response->

                        try {
                            if (obj != null) {
                                if(obj.has("id")){
                                    Log.d("FACEBOOKDATA",obj.getString("name"))
                                    Log.d("FACEBOOKDATA",obj.getString("email"))
                                   // Log.d("FACEBOOKDATA",JSONObject(obj.getString("picture")).getJSONObject(
                                        "data"
                                    //).getString("url"))

                                }
                            }
                        }catch (e:Exception){

                        }
                    }

                    val param = Bundle()
                    param.putString("fields","id,name,email")
                    graphRequest.parameters = param
                    graphRequest.executeAsync()
                }

            })
        }

    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "login Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, AfterLogin::class.java)
                    startActivity(intent)
                    finish()


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Enter Correct Email and Password", Toast.LENGTH_LONG).show()

                }
            }

    }

    override fun onSuccess(message: String) {
        login(inputEmail.text.toString(),inputPassword.text.toString())

    }

    override fun OnError(message: String) {
        Toast.makeText(this, "Enter Correct Email and Password", Toast.LENGTH_LONG).show()
    }

    override fun OnRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }











    /*
    val sharedPreferences: SharedPreferences = this.getSharedPreferences(
        "kotlinsharedpreference",
        Context.MODE_PRIVATE
    )

    //For checking status of checkBox if checkBox true then directly redirect to Afterlogin activity window
    val sharedCheckedBoxValue = sharedPreferences.getString("checkBox", "false")
    if (sharedCheckedBoxValue.equals("true")) {
        val intent = Intent(this, AfterLogin::class.java)
        startActivity(intent)
    } else {
        Toast.makeText(this, "Please Login In", Toast.LENGTH_LONG).show()
    }

    //CheckBox listener for checking user checking state of CheckBox
    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
        if (checkBox.isChecked) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("checkBox", "true")
            editor.apply()
            Toast.makeText(this, "Checked", Toast.LENGTH_LONG).show()
        } else {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("checkBox", "false")
            editor.apply()
            Toast.makeText(this, "Checked", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Unchecked", Toast.LENGTH_LONG).show()
        }
    }

    //login Button to redirect Aferlogin activity  if email and password match
    Login.setOnClickListener {
        val sharedUserName = sharedPreferences.getString("user_name", "defaultusername")
        val sharedEmailId = sharedPreferences.getString("email_id", "defaultname")
        val sharedPassword = sharedPreferences.getString("password", "defaultpassword")
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        if (sharedEmailId.equals(email) && sharedPassword.equals(password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
            val intent = Intent(this, AfterLogin::class.java)
            startActivity(intent)
             }
        else
            Toast.makeText(this, "User Not Found", Toast.LENGTH_LONG).show()


    }

    //sign up button to redirect signup activity window
    signup.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

     */
}