package com.tensioner.application


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tensioner.application.models.BlogPost




class BlogRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
   private lateinit var mListener : onItemClickListener
   interface onItemClickListener{
       fun onItemClick(position: Int)
   }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener= listener
    }

    private var items: List<BlogPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item, parent, false),
            mListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is BlogViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<BlogPost>){
        items = blogList
    }

    class BlogViewHolder
    constructor(
        itemView: View,listener: onItemClickListener
    ): RecyclerView.ViewHolder(itemView){

        private val blog_image: ImageView = itemView.findViewById<ImageView>(R.id.blog_image)
        private val blog_title: TextView = itemView.findViewById<TextView>(R.id.blog_title)
        private val blog_author: TextView = itemView.findViewById<TextView>(R.id.blog_author)

        fun bind(blogPost: BlogPost){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blog_image)
            blog_title.setText(blogPost.title)
            blog_author.setText(blogPost.username)

        }

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

}