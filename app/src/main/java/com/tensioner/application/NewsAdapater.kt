package com.tensioner.application

import android.app.Activity
import android.app.Application
import android.app.DownloadManager
import android.app.PendingIntent.getActivity
import android.app.usage.ExternalStorageStats
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.facebook.FacebookSdk.getApplicationContext
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class NewsAdapater(val article: List<Article>) : RecyclerView.Adapter<NewsAdapater.ArticleViewHolder>(){

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsimage)
        var author: TextView = itemView.findViewById(R.id.newsauthor)
        var title = itemView.findViewById<TextView>(R.id.newstitle)
        var date = itemView.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.newslayout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = article[position]
        holder.author.text=article.author
        holder.title.text=article.title
        holder.date.text=article.publishedAt

        if(profile_fregment.checkForInternet(getApplicationContext())){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
            Glide.with(holder.newsImage.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(article.urlToImage)
                .into(holder.newsImage)
        }
        else {
            val url = article.urlToImage
            var url1: String? = null
            url1 = url!!.substring(url.lastIndexOf("/") + 1, url.length)
                val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"$url1")
                val requestOptions = RequestOptions()
                   .placeholder(R.drawable.error)
                    .error(R.drawable.error)

                Glide.with(holder.newsImage.context)
                   .applyDefaultRequestOptions(requestOptions)
                    .load(file)
                    .into(holder.newsImage)
        }





        //listener for load entire news in the Webview
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,article.author,Toast.LENGTH_SHORT).show()
            val intent = Intent(getApplicationContext(),NewsDetails::class.java)
            intent.putExtra("url",article.url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            getApplicationContext().startActivity(intent)

        }



    }

    override fun getItemCount(): Int {
        return article.size

    }

}