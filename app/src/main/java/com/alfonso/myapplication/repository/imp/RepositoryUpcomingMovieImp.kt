package com.alfonso.myapplication.repository.imp

import com.alfonso.myapplication.repository.IRepositoryUpcomingMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.api.RemoteResponse
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.model.TypeMovie
import com.alfonso.myapplication.repository.model.Movie

class RepositoryUpcomingMovieImp(private val remoteMovieDataBase: IRemoteMovieDataBase, localMovie : ILocalMovie) : GeneralRepository(TypeMovie.UPCOMING,localMovie,
    { remoteMovieDataBase.getUpcoming() }), IRepositoryUpcomingMovie {

    override suspend fun getUpcomingMovies(): RepositoryResult {
        return getMovie()
    }

    override suspend fun refresh() : RepositoryResult {
        return refreshMovies()
    }
}