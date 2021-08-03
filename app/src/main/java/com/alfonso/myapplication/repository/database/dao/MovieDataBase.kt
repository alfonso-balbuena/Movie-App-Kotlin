package com.alfonso.myapplication.repository.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfonso.myapplication.repository.database.model.MovieDB

@Database(entities = [MovieDB::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao
}