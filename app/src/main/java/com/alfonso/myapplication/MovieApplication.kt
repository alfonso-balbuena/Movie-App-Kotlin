package com.alfonso.myapplication

import android.app.Application
import androidx.room.Room
import com.alfonso.myapplication.repository.api.IMovieDataBase
import com.alfonso.myapplication.repository.database.dao.MovieDataBase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {

    lateinit var database : MovieDataBase
    lateinit var remoteMovieDataBase : IMovieDataBase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,MovieDataBase::class.java,"CacheMovie").build()
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .build()
        remoteMovieDataBase = retrofit.create(IMovieDataBase::class.java)
    }
}