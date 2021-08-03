package com.alfonso.myapplication.repository.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class MovieDataBaseTest {

    companion object {
        const val API_KEY : String = "" //Put your api key from the movie DB
    }

    private lateinit var movieRest : IMovieDataBase


    @Before
    fun initMovieRest() {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .build()
        movieRest = retrofit.create(IMovieDataBase::class.java)
    }

    @Test
    fun getMostPopularTest() = runBlocking {
        val results = movieRest.getMostPopular(API_KEY,"en-US",1)
        assertEquals(1,results.page)
        assertTrue(results.results.isNotEmpty())
    }

    @Test
    fun getNowPlayingTesT() = runBlocking {
        val results = movieRest.getNowPlaying(API_KEY,"en-US",1)
        assertEquals(1,results.page)
        assertTrue(results.results.isNotEmpty())
    }

    @Test
    fun getUpcomingTest() = runBlocking {
        val results = movieRest.getUpcoming(API_KEY,"en-US",1)
        assertEquals(1,results.page)
        assertTrue(results.results.isNotEmpty())
    }
}