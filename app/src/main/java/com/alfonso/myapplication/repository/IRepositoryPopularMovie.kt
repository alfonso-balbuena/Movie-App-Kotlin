package com.alfonso.myapplication.repository

interface IRepositoryPopularMovie {
    suspend fun getPopularMovies() : RepositoryResult
    suspend fun refresh() : RepositoryResult
}