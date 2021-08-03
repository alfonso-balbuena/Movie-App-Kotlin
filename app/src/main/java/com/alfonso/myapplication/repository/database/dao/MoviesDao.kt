package com.alfonso.myapplication.repository.database.dao

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie

@Dao
interface MoviesDao {

    @Insert
    suspend fun insertAll(vararg upcomingMovies: MovieDB)

    @Query("SELECT * FROM MovieDB where type = :typeMovie ")
    suspend fun getAllMoviesForType(typeMovie: TypeMovie) : List<MovieDB>

    @Query("SELECT * FROM MovieDB where id = :id")
    suspend fun getMovieById(id : Long) : MovieDB

    @Query("DELETE FROM MovieDB WHERE type = :typeMovie")
    suspend fun cleanType(typeMovie: TypeMovie)

    @VisibleForTesting
    @Query("DELETE FROM MovieDB")
    suspend fun cleanAll()

}