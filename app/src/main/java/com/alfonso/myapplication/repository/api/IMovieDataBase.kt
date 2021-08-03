package com.alfonso.myapplication.repository.api

import com.alfonso.myapplication.repository.api.model.NowPlayingRequest
import com.alfonso.myapplication.repository.api.model.PopularRequest
import com.alfonso.myapplication.repository.api.model.UpcomingRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieDataBase {

    @GET("popular")
    suspend fun getMostPopular(@Query("api_key")api_key : String,
                                @Query("language")language: String,
                                @Query("page")page : Int,) : PopularRequest

    @GET("upcoming")
    suspend fun getUpcoming(@Query("api_key")api_key : String,
                            @Query("language")language: String,
                            @Query("page")page : Int,) : UpcomingRequest

    @GET("now_playing")
    suspend fun getNowPlaying(@Query("api_key")api_key : String,
                              @Query("language")language: String,
                              @Query("page")page : Int,) : NowPlayingRequest

}