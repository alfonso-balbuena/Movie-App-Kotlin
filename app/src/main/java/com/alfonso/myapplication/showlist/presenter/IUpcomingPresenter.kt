package com.alfonso.myapplication.showlist.presenter

interface IUpcomingPresenter {
    fun getUpcomingMovies()
    fun refreshUpcomingMovies()
    fun onDestroy()
}