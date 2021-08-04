package com.alfonso.myapplication.showlist.presenter.imp

import com.alfonso.myapplication.repository.IRepositoryPopularMovie
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.IPopularPresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PopularPresenterImp(private val popularRepository: IRepositoryPopularMovie, viewPresenter: IListViewPresenter, dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    IPopularPresenter{

    private val generalPresenter = GeneralPresenter(dispatcher,viewPresenter)

    override fun getPopularMovies() {
        generalPresenter.getMovie {
            popularRepository.getPopularMovies()
        }
    }

    override fun refreshPopularMovies() {
        generalPresenter.refreshMovies {
            popularRepository.refresh()
        }
    }

    override fun onDestroy() {
        generalPresenter.onDestroyGeneral()
    }
}