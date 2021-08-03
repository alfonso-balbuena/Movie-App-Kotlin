package com.alfonso.myapplication.repository.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun getMovieDataBaseMock(mockServer : MockWebServer) : IMovieDataBase {
    val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    return Retrofit.Builder()
        .baseUrl(mockServer.url("/"))
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(IMovieDataBase::class.java)
}

fun getSuccessMostPopularRequest() : MockResponse {
    val mockResponse = MockResponse()
    mockResponse.setBody("{\n" +
            "    \"page\": 1,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"adult\": false,\n" +
            "            \"backdrop_path\": \"/gGTCDNEvwG848u34Op1nZNALLUr.jpg\",\n" +
            "            \"genre_ids\": [\n" +
            "                28,\n" +
            "                80,\n" +
            "                53\n" +
            "            ],\n" +
            "            \"id\": 385128,\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"F9\",\n" +
            "            \"overview\": \"Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.\",\n" +
            "            \"popularity\": 6347.342,\n" +
            "            \"poster_path\": \"/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg\",\n" +
            "            \"release_date\": \"2021-05-19\",\n" +
            "            \"title\": \"F9\",\n" +
            "            \"video\": false,\n" +
            "            \"vote_average\": 7.8,\n" +
            "            \"vote_count\": 1845\n" +
            "        } ] }")
    return mockResponse
}

fun get401() : MockResponse {
    val mockResponse = MockResponse()
    mockResponse.setResponseCode(401)
    mockResponse.setBody("{\n" +
            "    \"status_code\": 7,\n" +
            "    \"status_message\": \"Invalid API key: You must be granted a valid key.\",\n" +
            "    \"success\": false\n" +
            "}")
    return mockResponse
}

fun get404() : MockResponse {
    val mockResponse = MockResponse()
    mockResponse.setResponseCode(404)
    return mockResponse
}