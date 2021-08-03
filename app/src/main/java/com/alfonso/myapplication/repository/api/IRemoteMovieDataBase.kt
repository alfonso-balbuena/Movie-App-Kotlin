package com.alfonso.myapplication.repository.api

import com.alfonso.myapplication.repository.api.model.MovieApi

interface IRemoteMovieDataBase {

    suspend fun getMostPopular() : RemoteResponse
    suspend fun getUpcoming() : RemoteResponse
    suspend fun getNowPlaying() : RemoteResponse

}