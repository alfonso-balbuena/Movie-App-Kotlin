package com.alfonso.myapplication.showlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.alfonso.myapplication.R
import com.alfonso.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private val fragmentList : List<Fragment> = listOf(PopularMoviesFragment(),UpcomingMoviesFragment(),NowPlayingFragment())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.bottomLayout.setOnItemSelectedListener {
            changeFragmentBottomNav(it.itemId)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.bottomLayout.selectedItemId = R.id.page_popular
    }

    private fun changeFragmentBottomNav(itemId : Int) : Boolean {
        return when(itemId) {
            R.id.page_popular -> {
                showFragment(fragmentList[0])
                true
            }
            R.id.page_upcoming -> {
                showFragment(fragmentList[1])
                true
            }
            R.id.page_now -> {
                showFragment(fragmentList[2])
                true
            }
            else -> false
        }
    }


    private fun showFragment(fragment : Fragment) {
        childFragmentManager.popBackStack()
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.content_page,fragment,"Tab")
            addToBackStack(null)
        }
    }
}