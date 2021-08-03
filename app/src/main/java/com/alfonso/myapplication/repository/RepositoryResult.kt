package com.alfonso.myapplication.repository

import com.alfonso.myapplication.repository.model.Movie

sealed interface RepositoryResult {
    class Success(val movies: List<Movie>) : RepositoryResult
    object Error : RepositoryResult
}