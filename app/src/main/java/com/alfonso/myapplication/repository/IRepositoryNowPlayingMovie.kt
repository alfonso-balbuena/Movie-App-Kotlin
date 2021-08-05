package com.alfonso.myapplication.repository

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.imp.RepositoryNowPlayingMovieImp

interface IRepositoryNowPlayingMovie {
    suspend fun getNowPlayingMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult

    companion object {
        fun get(app : MovieApplication) : IRepositoryNowPlayingMovie {
            return RepositoryNowPlayingMovieImp(IRemoteMovieDataBase.get(app), ILocalMovie.get(app))
        }
    }
}