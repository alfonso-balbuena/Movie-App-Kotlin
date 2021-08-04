package com.alfonso.myapplication.showlist.presenter.imp

import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import kotlinx.coroutines.*

open class GeneralPresenter(private val dispatcher: CoroutineDispatcher = Dispatchers.IO,private val viewPresenter: IListViewPresenter) {
    private val coroutineScope : CoroutineScope = CoroutineScope(Job() + dispatcher)

    fun getMovie(getDataLambda: suspend () -> RepositoryResult) {
        coroutineScope.launch {
            viewPresenter.showLoading()
            val resultMovies = getDataLambda()
            viewPresenter.hideLoading()
            when (resultMovies) {
                is RepositoryResult.Success -> viewPresenter.updateList(resultMovies.movies)
                is RepositoryResult.Error -> viewPresenter.showError()
            }
        }
    }

    fun refreshMovies(getRefreshLambda: suspend () -> RepositoryResult) {
        coroutineScope.launch {
            viewPresenter.showLoading()
            val resultRefresh = getRefreshLambda()
            viewPresenter.hideLoading()
            when(resultRefresh) {
                is RepositoryResult.Success -> viewPresenter.updateList(resultRefresh.movies)
                is RepositoryResult.Error -> viewPresenter.showError()
            }
        }
    }

    fun onDestroyGeneral() {
        coroutineScope.cancel()
    }
}