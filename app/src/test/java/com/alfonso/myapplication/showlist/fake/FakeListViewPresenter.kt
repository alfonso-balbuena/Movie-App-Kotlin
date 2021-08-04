package com.alfonso.myapplication.showlist.fake

import com.alfonso.myapplication.repository.model.Movie
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter

class FakeListViewPresenter : IListViewPresenter {

    var showedError : Boolean = false
    var isShowingLoading : Boolean = false
    var list : List<Movie> = ArrayList()

    override fun hideLoading() {
        isShowingLoading = false
    }

    override fun showLoading() {
        isShowingLoading = true
    }

    override fun showError() {
        showedError = true
    }

    override fun updateList(data: List<Movie>) {
        list = data
    }
}