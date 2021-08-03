package com.alfonso.myapplication.repository.imp

import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.api.RemoteResponse
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.model.TypeMovie
import com.alfonso.myapplication.repository.model.Movie

open class GeneralRepository(protected val type: TypeMovie,protected val localMovie : ILocalMovie,protected val remoteRequest: suspend () -> RemoteResponse) {

    suspend fun getMovie() : RepositoryResult {
        val movies = localMovie.getAllMoviesForType(type)
        return if(movies.isEmpty()) {
            when(val responseRemote = remoteRequest()) {
                is RemoteResponse.Success -> {
                    localMovie.insertAll(responseRemote.movies.map { it.toMovieDB(type) })
                    RepositoryResult.Success(localMovie.getAllMoviesForType(type).map { Movie.fromMovieDB(it) })
                }
                is RemoteResponse.Error -> RepositoryResult.Error
            }
        } else RepositoryResult.Success(movies.map { Movie.fromMovieDB(it) })
    }

    suspend fun refreshMovies() : RepositoryResult {
        localMovie.cleanType(type)
        return getMovie()
    }
}