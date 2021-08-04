package com.alfonso.myapplication.showlist.presenter

interface IPopularPresenter {
    fun getPopularMovies()
    fun refreshPopularMovies()
    fun onDestroy()
}