package com.alfonso.myapplication.repository

interface IRepositoryUpcomingMovie {
    suspend fun getUpcomingMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult
}