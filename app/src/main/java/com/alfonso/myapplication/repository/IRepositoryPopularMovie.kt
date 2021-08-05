package com.alfonso.myapplication.repository

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.imp.RepositoryPopularMovieImp

interface IRepositoryPopularMovie {
    suspend fun getPopularMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult

    companion object {
        fun get(app : MovieApplication) : IRepositoryPopularMovie {
            return RepositoryPopularMovieImp(IRemoteMovieDataBase.get(app), ILocalMovie.get(app))
        }
    }
}