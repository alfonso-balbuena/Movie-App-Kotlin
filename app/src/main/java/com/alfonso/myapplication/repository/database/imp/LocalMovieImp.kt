package com.alfonso.myapplication.repository.database.imp

import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.dao.MovieDataBase
import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie

class LocalMovieImp(private val dataBase : MovieDataBase) : ILocalMovie {

    override suspend fun insertAll(movies: List<MovieDB>) {
        dataBase.moviesDao().insertAll(*movies.toTypedArray())
    }

    override suspend fun getAllMoviesForType(typeMovie: TypeMovie): List<MovieDB> {
        return dataBase.moviesDao().getAllMoviesForType(typeMovie)
    }

    override suspend fun getMovieById(id: Long): MovieDB? {
        return dataBase.moviesDao().getMovieById(id)
    }

    override suspend fun cleanType(typeMovie: TypeMovie) {
        dataBase.moviesDao().cleanType(typeMovie)
    }
}