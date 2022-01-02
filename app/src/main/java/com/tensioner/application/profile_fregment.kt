package com.tensioner.application

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.view.View.GONE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.FacebookSdk.getApplicationContext
import com.tensioner.application.Interface.NewsService
import kotlinx.android.synthetic.main.fragment_profile_fregment.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profile_fregment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profile_fregment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter:NewsAdapater
    lateinit var swipe:SwipeRefreshLayout
    lateinit var progress:ProgressBar
    lateinit var progress1:ProgressBar
    private lateinit var recView: RecyclerView
    private lateinit var profiletoolbar: androidx.appcompat.widget.Toolbar
    lateinit var list1:List<Article>
    lateinit var menu: Menu
    lateinit var item1: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //For checking internet availability
    private fun checkInternet(){
        if (checkForInternet(getApplicationContext())) {
            Toast.makeText(activity, "Connected", Toast.LENGTH_SHORT).show()
            getNews()


        } else {
            Toast.makeText(activity, "Disconnected", Toast.LENGTH_SHORT).show()
            val db = DBHelper(getApplicationContext(), null)
            val data = db.getName()
            Log.d("databaseDhyani", data.toString())
            val list = ArrayList<Article>()
            if (data != null) {
                while(data.moveToNext()){
                    val ab = data.getString(0)
                    Log.d("databaseData",ab)
                    list.add(Article(
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getString(5),
                        data.getString(6)))
                    Log.d("DhyaniList",list.toString())

                }
                adapter = NewsAdapater(list)
                recView.adapter = adapter
                recView.layoutManager = LinearLayoutManager(activity)
                adapter.notifyDataSetChanged()
                progress.visibility=GONE
                recView.visibility=View.VISIBLE

            }
        }
    }


    @SuppressLint("Range")
    private suspend fun download() {
        var totalFile: Int = list1.size
        var fileDownload: Int = 0
        for (i in list1.indices) {
            val url = list1[i].urlToImage
            var url1: String? = null
            if (url != null) {
                url1 = url.substring(url.lastIndexOf("/") + 1, url.length)

                val dm =
                    getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val request = DownloadManager.Request(Uri.parse(url))
                //request.setTitle("News image")
                request.setMimeType("image/jpeg")
                //request.setDescription("image is downloading...")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "$url1")
                val downloadId = dm.enqueue(request)
                var downloading: Boolean = true
                while (downloading) {
                    val q = DownloadManager.Query()
                    q.setFilterById(downloadId)
                    val cursor: Cursor = dm.query(q)
                    cursor.moveToFirst()
                    val bytesDownloaded =
                        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                    val bytesTotal =
                        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                    val progress = bytesDownloaded * 100 / bytesTotal

                    Log.d("Current_Progress", progress.toString())



                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                        fileDownload++
                    }
                    cursor.close()

                }

                    Log.d("Progress_Overall", fileDownload.toString())
                progress1.progress = fileDownload*5

            }

        }

            Log.d("Progress", "Download completed")




    }

    //Main function for checking internet connection if it's connected using wifi as well as Cellular


    //News API Calling
    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("Dhyani", news.toString())
                    list1 = news.articles
                    adapter = NewsAdapater(news.articles)
                    newsrecycler.adapter = adapter
                    newsrecycler.layoutManager = LinearLayoutManager(activity)
                    adapter.notifyDataSetChanged()
                    progress.visibility=GONE
                    newsrecycler.visibility=View.VISIBLE
                    val db = DBHelper(getApplicationContext(), null)
                    for(i in news.articles.indices) {
                        var url = news.articles[i].urlToImage
                        var author = news.articles[i].author
                        if(url != null && author != null){
                            db.addName(news.articles[i].author!!,news.articles[i].title, news.articles[i].publishedAt,news.articles[i].url,
                                news.articles[i].urlToImage!!,news.articles[i].publishedAt)
                        }
                        else{
                            url = "abc"
                            author = "Author"
                            db.addName(author,news.articles[i].title, news.articles[i].publishedAt,news.articles[i].url,
                                url,news.articles[i].publishedAt)
                        }

                    }

                }


            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error", "Error in fetching news", t)
            }
        })


    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile_fregment, container, false)
        progress= view.findViewById(R.id.progress1)
        progress1= view.findViewById(R.id.progress2)
        swipe= view.findViewById(R.id.refresh1)
        profiletoolbar= view.findViewById(R.id.newstoolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(profiletoolbar)
        setHasOptionsMenu(true)
        menu = profiletoolbar.menu
        recView= view.findViewById(R.id.newsrecycler)

        checkInternet()

        swipe.setOnRefreshListener {
            newsrecycler.visibility=GONE
            progress.visibility=View.VISIBLE
            checkInternet()
            swipe.isRefreshing=false
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.newsmenu,menu)
        item1 = menu.findItem(R.id.newsdownload)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.newsdownload -> {

                Toast.makeText(
                    getApplicationContext(),
                    "Download Started",
                    Toast.LENGTH_SHORT
                ).show()
                progress1.visibility= View.VISIBLE
                GlobalScope.launch(IO) {
                    async (IO){
                        download()
                    }.await()
                    withContext(Main) {
                        item1.setIcon(R.drawable.file_download_done)
                        progress1.visibility= GONE
                        Toast.makeText(
                            activity,
                            "Download Completed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

            R.id.newssync -> {
                newsrecycler.visibility = GONE
                progress.visibility = View.VISIBLE
                GlobalScope.launch(Main) {
                    async { checkInternet() }.await()
                    swipe.isRefreshing = false
                    item1.setIcon(R.drawable.file_download)
                }


            }



        }
        return true

    }
    companion object {

        fun checkForInternet(context: Context): Boolean {

            // register activity with the connectivity manager service
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // if the android version is equal to M
            // or greater we need to use the
            // NetworkCapabilities to check what type of
            // network has the internet connection
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                // Returns a Network object corresponding to
                // the currently active default data network.
                val network = connectivityManager.activeNetwork ?: return false

                // Representation of the capabilities of an active network.
                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

                return when {
                    // Indicates this network uses a Wi-Fi transport,
                    // or WiFi has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                    // Indicates this network uses a Cellular transport. or
                    // Cellular has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                    // else return false
                    else -> false
                }
            } else {
                // if the android version is below M
                @Suppress("DEPRECATION") val networkInfo =
                    connectivityManager.activeNetworkInfo ?: return false
                @Suppress("DEPRECATION")
                return networkInfo.isConnected
            }
        }
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profile_fregment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile_fregment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}