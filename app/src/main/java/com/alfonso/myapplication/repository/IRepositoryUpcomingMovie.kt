package com.alfonso.myapplication.repository

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.imp.RepositoryUpcomingMovieImp

interface IRepositoryUpcomingMovie {
    suspend fun getUpcomingMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult

    companion object {
        fun get(app : MovieApplication) : IRepositoryUpcomingMovie {
            return RepositoryUpcomingMovieImp(IRemoteMovieDataBase.get(app), ILocalMovie.get(app))
        }
    }
}