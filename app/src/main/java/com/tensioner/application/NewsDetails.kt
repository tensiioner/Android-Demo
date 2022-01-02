package com.tensioner.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        val url = intent.getStringExtra("url")
        if(url!=null){
            newswebView.settings.javaScriptEnabled = true
            newswebView.settings.userAgentString="Mozilla/5.0 (Linux; Android 12) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Mobile Safari/537.36"
            newswebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    newsprogressBar.visibility= View.GONE
                    newswebView.visibility=View.VISIBLE

                }

            }
            newswebView.loadUrl(url)


        }
    }

    override fun onBackPressed() {
       if(newswebView.canGoBack()){
           newswebView.goBack()
       }else{
           super.onBackPressed()
       }
    }
}