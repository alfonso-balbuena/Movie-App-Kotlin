package com.alfonso.myapplication.showlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.R
import com.alfonso.myapplication.databinding.FragmentPopularMoviesBinding
import com.alfonso.myapplication.databinding.FragmentUpcomingMoviesBinding
import com.alfonso.myapplication.repository.model.Movie
import com.alfonso.myapplication.showlist.adapter.MovieAdapter
import com.alfonso.myapplication.showlist.adapter.MovieClickListener
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.IUpcomingPresenter

class UpcomingMoviesFragment : Fragment(), IListViewPresenter {

    private lateinit var upcomingPresenter : IUpcomingPresenter
    private lateinit var binding : FragmentUpcomingMoviesBinding
    private lateinit var adapter : MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingMoviesBinding.inflate(inflater,container,false)
        upcomingPresenter = IUpcomingPresenter.get(requireActivity().application as MovieApplication,this)
        adapter = MovieAdapter(MovieClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
            Log.d("Upcoming","$it")
        })
        binding.upcomingRecyclerView.adapter = adapter
        binding.swipeUpcoming.setOnRefreshListener {
            upcomingPresenter.refreshUpcomingMovies()
        }
        upcomingPresenter.getUpcomingMovies()
        return binding.root
    }

    override fun hideLoading() {
        binding.swipeUpcoming.isRefreshing = false
    }

    override fun showLoading() {
        binding.swipeUpcoming.isRefreshing = true
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun updateList(data: List<Movie>) {
        adapter.submitList(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        upcomingPresenter.onDestroy()
    }
}