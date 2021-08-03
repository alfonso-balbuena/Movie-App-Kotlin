package com.alfonso.myapplication.repository.api

import com.alfonso.myapplication.repository.api.imp.RemoteMovieDataBaseImp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteMovieDataBaseTest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var service : IMovieDataBase

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = getMovieDataBaseMock(mockWebServer)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getSuccessForMostPopular() = runBlocking {
        mockWebServer.enqueue(getSuccessMostPopularRequest())
        val remoteImp  = RemoteMovieDataBaseImp(service)
        val response = remoteImp.getMostPopular() as? RemoteResponse.Success
        Assert.assertNotNull(response)
        Assert.assertEquals(1,response!!.movies.size)
    }

    @Test
    fun requestMostPopularGetNoUnauthorizedResponse() = runBlocking {
        mockWebServer.enqueue(get401())
        val remoteImp  = RemoteMovieDataBaseImp(service)
        val response = remoteImp.getMostPopular() as? RemoteResponse.Error
        Assert.assertNotNull(response)
        Assert.assertEquals(true,response!!.throwable.message?.contains("401"))
    }

    @Test
    fun requestMostPopularGet404() = runBlocking {
        mockWebServer.enqueue(get404())
        val remoteImp  = RemoteMovieDataBaseImp(service)
        val response = remoteImp.getMostPopular() as? RemoteResponse.Error
        Assert.assertNotNull(response)
        Assert.assertEquals(true,response!!.throwable.message?.contains("404"))
    }

}