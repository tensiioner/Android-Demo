package com.tensioner.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_news_details.*
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_pdf.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var pdfwebview1: WebView
    lateinit var pdfrecyclerview: RecyclerView
    lateinit var adapter:PdfAdapter
    lateinit var Pdfprogess:ProgressBar
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
        val view: View = inflater.inflate(R.layout.fragment_pdf, container, false)
        pdfrecyclerview = view.findViewById(R.id.pdfrecyclerview)
        pdfwebview1 = view.findViewById(R.id.pdfwebview)
        Pdfprogess = view.findViewById(R.id.pdfprogressbar)
        val list = ArrayList<Pdf>()
        list.add(Pdf("Android",
            "https://www.vinayaktech.in/pdf/transistor.pdf"
            ))
        list.add(Pdf("Transistor",
            "https://www.vinayaktech.in/pdf/transistor.pdf"
        ))
        adapter = PdfAdapter(list)
        pdfrecyclerview.adapter = adapter
        pdfrecyclerview.layoutManager = LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()
        Pdfprogess.visibility= View.GONE
        pdfwebview1.visibility=View.GONE
        pdfrecyclerview.visibility=View.VISIBLE

        //For load PDF through in Webview through URL
        /*val url ="https://www.vinayaktech.in/pdf/transistor.pdf"
        pdfwebview1 = view.findViewById(R.id.pdfwebview)
        pdfwebview1.settings.javaScriptEnabled = true
        //pdfwebview1.settings.userAgentString="Mozilla/5.0 (Linux; Android 12) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Mobile Safari/537.36"
        pdfwebview1.setLayerType(View.LAYER_TYPE_HARDWARE,null)
        pdfwebview1.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pdfprogressbar.visibility=View.GONE
                pdfwebview1.visibility=View.VISIBLE
            }

        }
        //"https://drive.google.com/viewerng/viewer?embedded=true&url=
        pdfwebview1.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url")*/
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}