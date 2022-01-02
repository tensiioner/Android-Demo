package com.tensioner.application.Interface

import com.tensioner.application.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
//https://newsapi.org/v2/everything?q=Apple&from=2021-11-28&sortBy=popularity&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "01eff31b107b4048ba2dde0bff1d55a7"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String, @Query("page") page: Int): Call<News>
}
//https://newsapi.org/v2/top-headlines?apiKey=01eff31b107b4048ba2dde0bff1d55a7&country=in&page=1

object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}