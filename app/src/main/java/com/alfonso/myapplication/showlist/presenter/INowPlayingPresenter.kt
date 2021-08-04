package com.alfonso.myapplication.showlist.presenter

interface INowPlayingPresenter {
    fun getNowPlayingMovies()
    fun refreshNowPlayingMovies()
    fun onDestroy()
}