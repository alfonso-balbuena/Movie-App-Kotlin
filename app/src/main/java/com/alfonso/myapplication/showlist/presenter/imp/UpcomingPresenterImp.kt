package com.alfonso.myapplication.showlist.presenter.imp

import com.alfonso.myapplication.repository.IRepositoryUpcomingMovie
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.IUpcomingPresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UpcomingPresenterImp(private val upcomingRepository: IRepositoryUpcomingMovie, viewPresenter: IListViewPresenter, dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    IUpcomingPresenter{

    private val generalPresenter = GeneralPresenter(dispatcher,viewPresenter)

    override fun getUpcomingMovies() {
        generalPresenter.getMovie {
            upcomingRepository.getUpcomingMovies()
        }
    }

    override fun refreshUpcomingMovies() {
        generalPresenter.refreshMovies {
            upcomingRepository.refresh()
        }
    }

    override fun onDestroy() {
        generalPresenter.onDestroyGeneral()
    }
}