package com.alfonso.myapplication.repository.api.imp

import com.alfonso.myapplication.BuildConfig
import com.alfonso.myapplication.repository.api.IMovieDataBase
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.api.RemoteResponse

class RemoteMovieDataBaseImp(private val restMovieDataBase: IMovieDataBase) : IRemoteMovieDataBase {

    companion object {
        private const val page = 1
        private const val language = "en-US"
    }

    override suspend fun getMostPopular(): RemoteResponse {
        return try {
            val resultRequest = restMovieDataBase.getMostPopular(BuildConfig.API_KEY, language, page)
            RemoteResponse.Success(resultRequest.results)
        } catch (ex : Throwable) {
            RemoteResponse.Error(ex)
        }
    }

    override suspend fun getUpcoming(): RemoteResponse {
        return try {
            val resultRequest = restMovieDataBase.getUpcoming(BuildConfig.API_KEY, language, page)
            return RemoteResponse.Success(resultRequest.results)
        } catch (ex: Throwable) {
            RemoteResponse.Error(ex)
        }

    }

    override suspend fun getNowPlaying(): RemoteResponse {
        return try{
            val resultRequest = restMovieDataBase.getNowPlaying(BuildConfig.API_KEY, language, page)
            return RemoteResponse.Success(resultRequest.results)
        } catch (ex: Throwable) {
            RemoteResponse.Error(ex)
        }
    }


}