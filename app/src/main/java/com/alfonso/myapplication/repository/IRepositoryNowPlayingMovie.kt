package com.alfonso.myapplication.repository

interface IRepositoryNowPlayingMovie {
    suspend fun getNowPlayingMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult
}