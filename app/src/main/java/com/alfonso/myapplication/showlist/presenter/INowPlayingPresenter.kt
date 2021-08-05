package com.alfonso.myapplication.showlist.presenter

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.IRepositoryNowPlayingMovie
import com.alfonso.myapplication.showlist.presenter.imp.NowPlayingPresenterImp

interface INowPlayingPresenter {
    fun getNowPlayingMovies()
    fun refreshNowPlayingMovies()
    fun onDestroy()

    companion object {
        fun get(app : MovieApplication,viewPresenter: IListViewPresenter) : INowPlayingPresenter {
            return NowPlayingPresenterImp(IRepositoryNowPlayingMovie.get(app),viewPresenter)
        }
    }
}