package com.alfonso.myapplication.repository.database.mock

import com.alfonso.myapplication.repository.database.ILocalMovie
import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie

class MockLocalMovie : ILocalMovie {

    private val list = ArrayList<MovieDB>()
    private var cont : Int = 1
    override suspend fun insertAll(movies: List<MovieDB>) {
        movies.forEach { it.id = cont++.toLong() }
        list.addAll(movies)
    }

    override suspend fun getAllMoviesForType(typeMovie: TypeMovie): List<MovieDB> {
        return list.filter { it.type == typeMovie }
    }

    override suspend fun getMovieById(id: Long): MovieDB? {
        return list.find { it.id == id }
    }

    override suspend fun cleanType(typeMovie: TypeMovie) {
        list.removeIf { it.type == typeMovie }
    }
}