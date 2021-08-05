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
import com.alfonso.myapplication.databinding.FragmentNowPlayingBinding
import com.alfonso.myapplication.repository.model.Movie
import com.alfonso.myapplication.showlist.adapter.MovieAdapter
import com.alfonso.myapplication.showlist.adapter.MovieClickListener
import com.alfonso.myapplication.showlist.presenter.IListViewPresenter
import com.alfonso.myapplication.showlist.presenter.INowPlayingPresenter
import com.alfonso.myapplication.showlist.presenter.IPopularPresenter

class NowPlayingFragment() : Fragment(), IListViewPresenter {
    private lateinit var binding : FragmentNowPlayingBinding
    private lateinit var adapter : MovieAdapter
    private lateinit var playingPresenter : INowPlayingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNowPlayingBinding.inflate(inflater,container,false)
        playingPresenter = INowPlayingPresenter.get(requireActivity().application as MovieApplication,this)
        adapter = MovieAdapter(MovieClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
            Log.d("Upcoming","Click on $it")
        })
        binding.playingRecyclerView.adapter = adapter
        binding.swipePlaying.setOnRefreshListener {
            playingPresenter.refreshNowPlayingMovies()
        }
        playingPresenter.getNowPlayingMovies()
        return binding.root
    }

    override fun hideLoading() {
        binding.swipePlaying.isRefreshing = false
    }

    override fun showLoading() {
        binding.swipePlaying.isRefreshing = true
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun updateList(data: List<Movie>) {
        adapter.submitList(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        playingPresenter.onDestroy()
    }
}