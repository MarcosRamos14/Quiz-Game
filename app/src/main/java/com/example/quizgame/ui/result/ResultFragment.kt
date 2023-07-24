package com.example.quizgame.ui.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentResultBinding
import com.example.quizgame.ui.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private val viewModel: QuizViewModel by viewModels()
    private val args by navArgs<ResultFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        val resultViewArgs = args.resultViewArgs
        setView(resultViewArgs)
        setListeners()
    }

    private fun setView(resultViewArgs: ResultViewArgs) {
        val correctAnswers = resultViewArgs.correctAnswers
        val incorrectAnswers = resultViewArgs.incorrectAnswers

        with(binding) {
            textResultHits.text = getString(R.string.result_text_total_hits, correctAnswers)
            textResultErrors.text = getString(R.string.result_text_total_hits, incorrectAnswers)
        }
    }

    private fun setListeners() {
        with(binding) {
            btnConclude.setOnClickListener {
                viewModel.resetGame()
                findNavController().popBackStack(R.id.homeFragment, false)
            }
            btnRestart.setOnClickListener {
                viewModel.resetGame()
                findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
            }
        }
    }
}