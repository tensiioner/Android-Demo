package com.tensioner.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.tensioner.application.HomeFragment.Companion.newInstance
import com.tensioner.application.Product_Description.Companion.newInstance
import kotlinx.android.synthetic.main.fragment_after_login_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AfterLoginMain.newInstance] factory method to
 * create an instance of this fragment.
 */
class AfterLoginMain : Fragment() {
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_after_login_main, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel("https://image.freepik.com/free-vector/sale-banner-with-product-description_1361-1333.jpg"))
        imageList.add(SlideModel("https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/153405588/original/04c69b9760194c4b5f60e9212c131e6a6534f45d/design-your-3d-part-or-instrument-in-solidworks.jpg"))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDRaYuzGERGfqe3lRkWckruzbf7Agq13IO6XRl4mfegJV-77TM1oOk1UnuwEKXUfm5aa0&usqp=CAU"))
        imageList.add(SlideModel("https://img.freepik.com/free-psd/furniture-sale-facebook-cover-template_220159-62.jpg?size=626&ext=jpg"))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfQAVHWbTsARYqjmiAz6BWSbgnZzZ8N7mdXA&usqp=CAU"))

        val imageSlider: ImageSlider = view.findViewById(R.id.image_slider)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        val image:ImageView = view.findViewById(R.id.imageView2)
        image.setOnClickListener{

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.productFragment,HomeFragment()).addToBackStack(null)
                .commit()
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
         * @return A new instance of fragment AfterLoginMain.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AfterLoginMain().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}