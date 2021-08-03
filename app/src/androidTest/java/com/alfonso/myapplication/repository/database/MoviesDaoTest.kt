package com.alfonso.myapplication.repository.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alfonso.myapplication.repository.database.dao.MovieDataBase
import com.alfonso.myapplication.repository.database.dao.MoviesDao
import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {
    private lateinit var moviesDao: MoviesDao
    private lateinit var db : MovieDataBase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,MovieDataBase::class.java).build()
        moviesDao = db.moviesDao()
    }


    @After
    fun finish() = runBlocking {
        moviesDao.cleanAll()
        db.close()
    }

    private fun makeMovieDBObject(type : TypeMovie) : MovieDB {
        return MovieDB(0,3,false,"test_path", "1,2,3","english",
        "Test movie","This movie is just for testing",4.9,"poster_path","",
        "Test movie",false,4.9,5000,type)
    }

    @Test
    fun insertUpcomingMovie() = runBlocking {
        val movieObject = makeMovieDBObject(TypeMovie.UPCOMING)
        moviesDao.insertAll(movieObject)
        val results = moviesDao.getAllMoviesForType(TypeMovie.UPCOMING)
        Assert.assertFalse(results.isEmpty())
        Assert.assertEquals(1,results.size)
    }

    @Test
    fun insertUpcomingAndPopularMovieGetOnlyPopular() = runBlocking {
        val upcomingMovie = makeMovieDBObject(TypeMovie.UPCOMING)
        val popularMovie = makeMovieDBObject(TypeMovie.POPULAR)
        moviesDao.insertAll(upcomingMovie,popularMovie)
        val resultPopular = moviesDao.getAllMoviesForType(TypeMovie.POPULAR)
        Assert.assertFalse(resultPopular.isEmpty())
        Assert.assertEquals(1,resultPopular.size)
        Assert.assertEquals(TypeMovie.POPULAR,resultPopular[0].type)
    }

    @Test
    fun insertUpcomingCleanUpcomingNoData() = runBlocking {
        val upcomingMovie = makeMovieDBObject(TypeMovie.UPCOMING)
        moviesDao.insertAll(upcomingMovie)
        moviesDao.cleanType(TypeMovie.UPCOMING)
        val results = moviesDao.getAllMoviesForType(TypeMovie.UPCOMING)
        Assert.assertTrue(results.isEmpty())
    }

    @Test
    fun insertUpcomingAndPopularCleanUpcomingGetPopularNoEmpty() = runBlocking {
        val upcomingMovie = makeMovieDBObject(TypeMovie.UPCOMING)
        val popularMovie = makeMovieDBObject(TypeMovie.POPULAR)
        moviesDao.insertAll(upcomingMovie,popularMovie)
        moviesDao.cleanType(TypeMovie.UPCOMING)
        val resultsPopular = moviesDao.getAllMoviesForType(TypeMovie.POPULAR)
        Assert.assertTrue(resultsPopular.isNotEmpty())
        Assert.assertEquals(TypeMovie.POPULAR,resultsPopular[0].type)
    }

    @Test
    fun insertThreeUpcomingMoviesGetThreeUpcomingMovies() = runBlocking {
        val upcomingMovie1 = makeMovieDBObject(TypeMovie.UPCOMING)
        val upcomingMovie2 = makeMovieDBObject(TypeMovie.UPCOMING)
        val upcomingMovie3 = makeMovieDBObject(TypeMovie.UPCOMING)
        moviesDao.insertAll(upcomingMovie1,upcomingMovie2,upcomingMovie3)
        val resultsPopular = moviesDao.getAllMoviesForType(TypeMovie.UPCOMING)
        Assert.assertEquals(3,resultsPopular.size)
    }

    @Test
    fun insertNothingAndGetPopularEmptyList() = runBlocking {
        val result = moviesDao.getAllMoviesForType(TypeMovie.POPULAR)
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun idNoExistgetNull() = runBlocking {
        val result = moviesDao.getMovieById(3)
        Assert.assertNull(result)
    }
}