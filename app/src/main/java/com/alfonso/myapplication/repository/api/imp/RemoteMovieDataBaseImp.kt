package com.alfonso.myapplication.repository.api.imp

import com.alfonso.myapplication.repository.api.IMovieDataBase
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.api.RemoteResponse
import com.alfonso.myapplication.repository.api.model.MovieApi

class RemoteMovieDataBaseImp(private val restMovieDataBase: IMovieDataBase) : IRemoteMovieDataBase {

    companion object {
        private const val page = 1
        private const val language = "en-US"
    }

    override suspend fun getMostPopular(): RemoteResponse {
        return try {
            val resultRequest = restMovieDataBase.getMostPopular("", language, page)
            RemoteResponse.Success(resultRequest.results)
        } catch (ex : Throwable) {
            RemoteResponse.Error(ex)
        }
    }

    override suspend fun getUpcoming(): RemoteResponse {
        return try {
            val resultRequest = restMovieDataBase.getUpcoming("", language, page)
            return RemoteResponse.Success(resultRequest.results)
        } catch (ex: Throwable) {
            RemoteResponse.Error(ex)
        }

    }

    override suspend fun getNowPlaying(): RemoteResponse {
        return try{
            val resultRequest = restMovieDataBase.getNowPlaying("", language, page)
            return RemoteResponse.Success(resultRequest.results)
        } catch (ex: Throwable) {
            RemoteResponse.Error(ex)
        }
    }


}