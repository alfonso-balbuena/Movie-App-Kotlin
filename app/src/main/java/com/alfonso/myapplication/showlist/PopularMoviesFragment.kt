package com.alfonso.myapplication.showlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alfonso.myapplication.MovieApplication
import com.alfonso.myapplication.databinding.FragmentPopularMoviesBinding
import com.alfonso.myapplication.repository.model.Movie
import com.alfonso.myapplication.showlist.adapter.MovieAdapter
import com.alfonso.myapplication.showlist.adapter.MovieClickListener
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.IPopularPresenter

class PopularMoviesFragment : Fragment(), IListViewPresenter {

    private lateinit var binding : FragmentPopularMoviesBinding
    private lateinit var adapter : MovieAdapter
    private lateinit var popularPresenter : IPopularPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater,container,false)
        popularPresenter = IPopularPresenter.get(requireActivity().application as MovieApplication,this)
        adapter = MovieAdapter(MovieClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
            Log.d("PopularMoviesFragment","Click on $it")
        })
        binding.popularRecyclerView.adapter = adapter
        binding.swipePopular.setOnRefreshListener {
            popularPresenter.refreshPopularMovies()
        }
        popularPresenter.getPopularMovies()
        return binding.root
    }



    override fun hideLoading() {
        binding.swipePopular.isRefreshing = false
    }

    override fun showLoading() {
        binding.swipePopular.isRefreshing = true
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun updateList(data: List<Movie>) {
        adapter.submitList(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        popularPresenter.onDestroy()
    }
}