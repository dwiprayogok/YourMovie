package com.example.yourmovie.Interface

import com.example.yourmovie.Model.GetMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "6f92b079339b4084dc76432952e9a3ef",
        @Query("page") page: Int
    ): Call<GetMovieResponse>


    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "6f92b079339b4084dc76432952e9a3ef",
        @Query("page") page: Int
    ): Call<GetMovieResponse>
}