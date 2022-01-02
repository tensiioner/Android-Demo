package com.tensioner.application

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Pdesc : AppCompatActivity() {

    private var button: Boolean= false
    private lateinit var title1: TextView
    private lateinit var price: TextView
    private lateinit var description: TextView
    private lateinit var cart: Button
    private lateinit var color1: Button
    private lateinit var color2: Button
    private lateinit var color3: Button
    private lateinit var likeB: ImageButton
    private lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdesc)
        supportActionBar?.hide()
        findView()
        setListener()

    }

    private fun setListener() {
        likeB.setOnClickListener{
            if(button==false) {
                likeB.setImageResource(R.drawable.likelike)
                Toast.makeText(applicationContext,"Added to Wishlist", Toast.LENGTH_SHORT).show()
                button=true
            }
            else {
                likeB.setImageResource(R.drawable.like)
                Toast.makeText(applicationContext,"Remove From Wishlist", Toast.LENGTH_SHORT).show()
                button = false
            }

        }

        cart.setOnClickListener{

            Toast.makeText(applicationContext,"Cart", Toast.LENGTH_SHORT).show()
        }

        color1.setOnClickListener{
            image.setImageResource(R.drawable.purple)

        }
        color2.setOnClickListener{
            image.setImageResource(R.drawable.celeronx)

        }
        color3.setOnClickListener{

            image.setImageResource(R.drawable.black)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun findView() {


        title1 = findViewById(R.id.product_name)
        price = findViewById(R.id.price)
        description = findViewById(R.id.description)
        likeB = findViewById(R.id.likeButton)
        cart = findViewById(R.id.addToCartButton)
        color1 = findViewById(R.id.circle1)
        color2 = findViewById(R.id.colourY)
        color3 = findViewById(R.id.colourB)
        image = findViewById(R.id.photo)
        image.setImageResource(R.drawable.purple)
        price.text = "Rs.1500/-"
        description.text = "• Embroidered graphic on front crest\n" +
                "• Printed graphic on back\n" +
                "• Double-Needle sleeve and bottom hem\n" +
                "• Preshrunk to minimize shrinkage\n" +
                "• Set-in Rib Collar with Shoulder to Shoulder Taping\n" +
                "• Sizes: S-M-L-XL-2XL\n" +
                "• Embroidered graphic on front crest\n" +
                "• Printed graphic on back\n" +
                "• Double-Needle sleeve and bottom hem\n" +
                "• Preshrunk to minimize shrinkage\n" +
                "• Set-in Rib Collar with Shoulder to Shoulder Taping\n" +
                "• Sizes: S-M-L-XL-2XL\n" +
                "• Embroidered graphic on front crest\n" +
                "• Printed graphic on back\n" +
                "• Double-Needle sleeve and bottom hem\n" +
                "• Preshrunk to minimize shrinkage\n" +
                "• Set-in Rib Collar with Shoulder to Shoulder Taping\n" +
                "• Sizes: S-M-L-XL-2XL\n" +
                "• Embroidered graphic on front crest\n" +
                "• Printed graphic on back\n" +
                "• Double-Needle sleeve and bottom hem\n" +
                "• Preshrunk to minimize shrinkage\n" +
                "• Set-in Rib Collar with Shoulder to Shoulder Taping\n" +
                "• Sizes: S-M-L-XL-2XL\n" +
                "Free Ground Shipping On Orders Over Rs.500"
    }
}