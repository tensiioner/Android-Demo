package com.tensioner.application

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var button: Boolean= false
private lateinit var title1: TextView
private lateinit var price: TextView
private lateinit var description: TextView
private lateinit var cart: Button
private lateinit var color1: Button
private lateinit var color2: Button
private lateinit var color3: Button
private lateinit var likeB: ImageButton
private lateinit var share: ImageButton
private lateinit var image: ImageView

/**
 * A simple [Fragment] subclass.
 * Use the [AfterProductPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class AfterProductPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }


    private fun setListener() {
        likeB.setOnClickListener{
            if(button==false) {
                likeB.setImageResource(R.drawable.likelike)
                Toast.makeText(activity,"Added to Wishlist", Toast.LENGTH_SHORT).show()
                button=true
            }
            else {
                likeB.setImageResource(R.drawable.like)
                Toast.makeText(activity,"Remove From Wishlist", Toast.LENGTH_SHORT).show()
                button = false
            }

        }

        share.setOnClickListener{
            val shareBody = "Check this out On FlipKart : https://play.google.com/store/apps/details?id=com.flipkart.android&hl=en_IN&gl=US"
            val shareText = "Check this out"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareText)
            startActivity(shareIntent)
        }

        cart.setOnClickListener{

            Toast.makeText(activity,"Cart", Toast.LENGTH_SHORT).show()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_after_product_page, container, false)
        title1 = view.findViewById(R.id.product_name)
        price = view.findViewById(R.id.price)
        share = view.findViewById(R.id.share)
        description = view.findViewById(R.id.description)
        likeB = view.findViewById(R.id.likeButton)
        cart = view.findViewById(R.id.addToCartButton)
        color1 = view.findViewById(R.id.circle1)
        color2 = view.findViewById(R.id.colourY)
        color3 = view.findViewById(R.id.colourB)
        image = view.findViewById(R.id.photo)
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
        setListener()
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AfterProductPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AfterProductPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}