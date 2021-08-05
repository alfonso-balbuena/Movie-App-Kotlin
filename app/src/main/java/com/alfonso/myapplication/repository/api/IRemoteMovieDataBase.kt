package com.alfonso.myapplication.repository.api

import android.app.Application
import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.api.imp.RemoteMovieDataBaseImp


interface IRemoteMovieDataBase {

    suspend fun getMostPopular() : RemoteResponse
    suspend fun getUpcoming() : RemoteResponse
    suspend fun getNowPlaying() : RemoteResponse

    companion object {
        fun get(app : MovieApplication) : IRemoteMovieDataBase {
            return RemoteMovieDataBaseImp(app.remoteMovieDataBase)
        }
    }

}