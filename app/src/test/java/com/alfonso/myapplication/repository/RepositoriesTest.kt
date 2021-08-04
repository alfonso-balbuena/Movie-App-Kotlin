package com.alfonso.myapplication.repository

import com.alfonso.myapplication.repository.api.mock.FakeRemoteMovieDataBase
import com.alfonso.myapplication.repository.database.mock.FakeLocalMovie
import com.alfonso.myapplication.repository.database.model.TypeMovie
import com.alfonso.myapplication.repository.imp.RepositoryPopularMovieImp
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoriesTest {
    private lateinit var repository : IRepositoryPopularMovie
    private lateinit var remoteRepository : FakeRemoteMovieDataBase
    private lateinit var localRep : FakeLocalMovie

    @Before
    fun setup() {
        remoteRepository = FakeRemoteMovieDataBase()
        localRep = FakeLocalMovie()
        repository = RepositoryPopularMovieImp(remoteRepository,localRep)
    }

    @Test
    fun getPopularMoviesSuccess() = runBlocking {
        val prevMovies = localRep.getAllMoviesForType(TypeMovie.POPULAR)
        Assert.assertTrue(prevMovies.isEmpty())
        val result = repository.getPopularMovies() as? RepositoryResult.Success
        Assert.assertNotNull(result)
        Assert.assertEquals(1,result!!.movies.size)
        val afterMovies = localRep.getAllMoviesForType(TypeMovie.POPULAR)
        Assert.assertTrue(afterMovies.isNotEmpty())
    }

    @Test
    fun getPopularMovieError() = runBlocking {
        remoteRepository.returnError = true
        val result = repository.getPopularMovies() as? RepositoryResult.Error
        Assert.assertNotNull(result)
    }

    @Test
    fun refreshPopularMovieSuccess() = runBlocking {
        val result = repository.getPopularMovies() as? RepositoryResult.Success
        Assert.assertNotNull(result)
        Assert.assertEquals(1,result!!.movies.size)
        Assert.assertEquals(1, result.movies[0].id)
        val resultRefresh = repository.refresh() as? RepositoryResult.Success
        Assert.assertNotNull(resultRefresh)
        Assert.assertEquals(1,resultRefresh!!.movies.size)
        Assert.assertEquals(2, resultRefresh.movies[0].id)
    }
}