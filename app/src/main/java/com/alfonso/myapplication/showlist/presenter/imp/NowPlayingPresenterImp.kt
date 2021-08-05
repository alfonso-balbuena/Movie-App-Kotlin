package com.alfonso.myapplication.showlist.presenter.imp

import com.alfonso.myapplication.repository.IRepositoryNowPlayingMovie
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.INowPlayingPresenter
import kotlinx.coroutines.*

class NowPlayingPresenterImp(private val nowPlayingRepository: IRepositoryNowPlayingMovie,private val viewPresenter: IListViewPresenter,private val dispatcher: CoroutineDispatcher = Dispatchers.Main)
    : INowPlayingPresenter {

    private val generalPresenter = GeneralPresenter(dispatcher,viewPresenter)

    override fun getNowPlayingMovies() {
        generalPresenter.getMovie {
            nowPlayingRepository.getNowPlayingMovies()
        }
    }

    override fun refreshNowPlayingMovies() {
        generalPresenter.refreshMovies {
            nowPlayingRepository.refresh()
        }
    }

    override fun onDestroy() {
        generalPresenter.onDestroyGeneral()
    }
}