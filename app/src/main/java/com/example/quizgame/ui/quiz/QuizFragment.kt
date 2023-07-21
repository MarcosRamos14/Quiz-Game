package com.example.quizgame.ui.quiz

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.AnswerDTO
import com.example.core.domain.model.QuestionDTO
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private val quizAdapter: QuizAdapter by lazy {
        val quizAdapter = QuizAdapter()
        binding.recyclerQuiz.adapter = quizAdapter
        return@lazy quizAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            findNavController().navigate(R.id.action_quizFragment_to_chooseOptionDialogFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuizBinding.bind(view)
        observerQuestionUiState()
    }

    private fun observerQuestionUiState() {
        with(viewModel) {
            getQuestion()
            state.observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    QuizViewModel.UiState.Loading -> FLIPPER_POSITION_LOADING

                    is QuizViewModel.UiState.Success -> {
                        quizAdapter.submitQuestion(uiState.question)
                        FLIPPER_CHILD_POSITION_SUCCESS
                    }

                    QuizViewModel.UiState.Error -> FLIPPER_CHILD_POSITION_ERROR
                }
            }
        }
    }

    companion object {
        private const val FLIPPER_POSITION_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_SUCCESS = 1
        private const val FLIPPER_CHILD_POSITION_ERROR = 2
    }
}