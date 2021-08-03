package com.alfonso.myapplication.repository.api

import com.alfonso.myapplication.repository.api.model.MovieApi

sealed class RemoteResponse {
    class Success(val movies:  List<MovieApi>) : RemoteResponse()
    class Error(val throwable: Throwable) : RemoteResponse()
}
