package com.alfonso.myapplication.repository.imp

import com.alfonso.myapplication.repository.IRepositoryNowPlayingMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.api.IRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.model.TypeMovie

class RepositoryNowPlayingMovieImp(private val remoteMovieDataBase: IRemoteMovieDataBase, localMovie : ILocalMovie) : GeneralRepository(TypeMovie.NOW_PLAYING,localMovie,
    { remoteMovieDataBase.getNowPlaying() }), IRepositoryNowPlayingMovie {

    override suspend fun getNowPlayingMovies(): RepositoryResult {
        return getMovie()
    }

    override suspend fun refresh(): RepositoryResult {
        return refreshMovies()
    }
}