package com.alfonso.myapplication.showlist.presenter

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.IRepositoryPopularMovie
import com.alfonso.myapplication.showlist.presenter.imp.PopularPresenterImp

interface IPopularPresenter {
    fun getPopularMovies()
    fun refreshPopularMovies()
    fun onDestroy()

    companion object {
        fun get(app : MovieApplication,viewPresenter: IListViewPresenter) : IPopularPresenter {
            return PopularPresenterImp(IRepositoryPopularMovie.get(app),viewPresenter)
        }
    }
}