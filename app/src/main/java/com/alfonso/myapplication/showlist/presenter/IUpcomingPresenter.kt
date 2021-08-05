package com.alfonso.myapplication.showlist.presenter

import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.repository.IRepositoryUpcomingMovie
import com.alfonso.myapplication.showlist.presenter.imp.UpcomingPresenterImp

interface IUpcomingPresenter {
    fun getUpcomingMovies()
    fun refreshUpcomingMovies()
    fun onDestroy()

    companion object {
        fun get(app : MovieApplication,viewPresenter: IListViewPresenter) : IUpcomingPresenter {
            return UpcomingPresenterImp(IRepositoryUpcomingMovie.get(app),viewPresenter)
        }
    }
}