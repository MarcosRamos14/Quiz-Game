package com.example.quizgame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.GameDTO
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

        homeAdapter.submitList(
            listOf(
                HomeAdapterItem.GameHeader("Rankin", "aaa", "dd"),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 1,true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 2,true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 3, true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 4, true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 5, true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 6, true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 7, true),
                HomeAdapterItem.GameItem(GameDTO(1, "Marcos", 32), 8, true)
            )
        )
    }

    private fun setupListener() {
        binding.btnPlay.setOnClickListener {
           findNavController().navigate(R.id.action_to_dialogInputFragment)
        }
    }
}