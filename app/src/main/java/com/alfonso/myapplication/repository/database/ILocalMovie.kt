package com.alfonso.myapplication.repository.database

import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie

interface ILocalMovie {
    suspend fun insertAll(movies: List<MovieDB>)
    suspend fun getAllMoviesForType(typeMovie: TypeMovie) : List<MovieDB>
    suspend fun getMovieById(id : Long) : MovieDB?
    suspend fun cleanType(typeMovie: TypeMovie)
}