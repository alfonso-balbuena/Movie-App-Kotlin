package com.alfonso.myapplication.repository.api


interface IRemoteMovieDataBase {

    suspend fun getMostPopular() : RemoteResponse
    suspend fun getUpcoming() : RemoteResponse
    suspend fun getNowPlaying() : RemoteResponse

}