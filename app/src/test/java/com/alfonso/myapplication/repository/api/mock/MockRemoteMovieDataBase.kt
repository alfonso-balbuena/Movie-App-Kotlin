package com.alfonso.myapplication.repository.api.mock

import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.api.RemoteResponse
import com.alfonso.myapplication.repository.api.model.MovieApi

class MockRemoteMovieDataBase : IRemoteMovieDataBase {

    var returnError = false

    override suspend fun getMostPopular(): RemoteResponse {
        return if(returnError) RemoteResponse.Error(Throwable())
        else RemoteResponse.Success(getMovieApiList())
    }

    override suspend fun getUpcoming(): RemoteResponse {
        return if(returnError) RemoteResponse.Error(Throwable())
        else RemoteResponse.Success(getMovieApiList())
    }

    override suspend fun getNowPlaying(): RemoteResponse {
        return if(returnError) RemoteResponse.Error(Throwable())
        else RemoteResponse.Success(getMovieApiList())
    }

    private fun getMovieApiList() : List<MovieApi> {
        return listOf(MovieApi(false,"/gGTCDNEvwG848u34Op1nZNALLUr.jpg", listOf(28,80),385128,
        "en","F9","Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
            6347.342,"/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg","2021-05-19","F9",false,7.8,1845))
    }
}