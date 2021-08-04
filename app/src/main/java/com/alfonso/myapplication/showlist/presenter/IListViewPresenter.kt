package com.alfonso.myapplication.showlist.presenter

import com.alfonso.myapplication.repository.model.Movie

interface IListViewPresenter {
    fun hideLoading()
    fun showLoading()
    fun showError()
    fun updateList(data : List<Movie>)
}