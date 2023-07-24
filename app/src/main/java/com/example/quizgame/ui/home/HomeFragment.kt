package com.example.quizgame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter: HomeAdapter by lazy {
        val homeAdapter = HomeAdapter()
        binding.recyclerRanking.adapter = homeAdapter
        return@lazy homeAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupListener()
    }

    private fun setupListener() {
        binding.btnPlay.setOnClickListener {
           findNavController().navigate(R.id.action_to_dialogInputFragment)
        }
    }
}