package com.tensioner.application

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext

class PdfAdapter(val pdf: List<Pdf>) : RecyclerView.Adapter<PdfAdapter.PdfViewHolder>() {

    class PdfViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var pdfname: TextView= itemView.findViewById(R.id.pdfname)
        var pdfdownload: ImageButton = itemView.findViewById(R.id.pdfdownload)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pdflayout,parent,false)
        return PdfViewHolder(view)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {

        val pdf = pdf[position]
        holder.pdfname.text=pdf.name

        holder.pdfdownload.setOnClickListener {
           val url: String = pdf.url
            var url1: String? = null
            if (url != null) {
                url1 = url.substring(url.lastIndexOf("/") + 1, url.length)
            }
            val dm =
                getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var request = DownloadManager.Request(
                Uri.parse(url))
            //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            request.setTitle("$url1")
            request.setMimeType("application/pdf")
            request.setDescription("Downloading....")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"$url1")
            dm.enqueue(request)
        }



    }

    override fun getItemCount(): Int {
        return pdf.size
    }
}