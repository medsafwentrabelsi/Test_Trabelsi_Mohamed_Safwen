package com.safwen.boxffficetest.data.remote

import com.safwen.boxffficetest.data.entities.MovieObject
import com.safwen.boxffficetest.data.entities.SearchMovieResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


interface BoxOfficeApi {
    @GET(".")
    fun getSearchedMovie(@Query("s") movieTitle: String?, @Query("apikey")apiKey:String?) : Observable<SearchMovieResponse>
    @GET(".")
    fun getMovieDetails(@Query("i") movieImd: String?, @Query("apikey")apiKey:String?) : Observable<MovieObject>

    companion object {

        fun create() : BoxOfficeApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("http://www.omdbapi.com/")
                .build()
            return retrofit.create(BoxOfficeApi::class.java)
        }
    }
}