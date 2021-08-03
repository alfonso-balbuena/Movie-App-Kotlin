package com.alfonso.myapplication.repository.imp

import com.alfonso.myapplication.repository.IRepositoryPopularMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.model.TypeMovie

class RepositoryPopularMovieImp(private val remoteMovieDataBase: IRemoteMovieDataBase, localMovie : ILocalMovie) : GeneralRepository(TypeMovie.POPULAR,localMovie,
    { remoteMovieDataBase.getMostPopular() }), IRepositoryPopularMovie {

    override suspend fun getPopularMovies(): RepositoryResult {
        return getMovie()
    }

    override suspend fun refresh(): RepositoryResult {
        return  refreshMovies()
    }
}