package com.example.quizgame.ui.quiz

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentQuizBinding
import com.example.quizgame.ui.result.ResultViewArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private val quizAdapter: QuizAdapter by lazy {
        val quizAdapter = QuizAdapter { selectedItem ->
            viewModel.submitAnswer(selectedItem)
        }
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
        setupListener()
        observerQuestionUiState()
        observerAnswerUiState()
        observerAnswerSelected()
    }

    private fun setupListener() {
        binding.btnNext.setOnClickListener {
            with(viewModel) {
                if (isGameFinished.value == true) {
                    navigateToResultIfGameFinished()
                } else {
                    getQuestion()
                    clearAnswerState()
                }
            }
        }
    }

    private fun observerQuestionUiState() {
        with(viewModel) {
            getQuestion()
            stateQuestion.observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    QuizViewModel.UiStateQuestion.Loading -> FLIPPER_POSITION_LOADING
                    is QuizViewModel.UiStateQuestion.Success -> {
                        quizAdapter.submitQuestion(uiState.question)
                        FLIPPER_CHILD_POSITION_SUCCESS
                    }
                    QuizViewModel.UiStateQuestion.Error -> FLIPPER_CHILD_POSITION_ERROR
                }
            }
        }
    }

    private fun observerAnswerUiState() {
        viewModel.stateAnswer.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                QuizViewModel.UiStateAnswer.Loading -> FLIPPER_POSITION_LOADING
                is QuizViewModel.UiStateAnswer.Success -> {
                    quizAdapter.submitQuestion(uiState.question, uiState.answerResult)
                    FLIPPER_CHILD_POSITION_SUCCESS
                }
                QuizViewModel.UiStateAnswer.Error -> FLIPPER_CHILD_POSITION_ERROR
            }
        }
    }

    private fun observerAnswerSelected() {
        viewModel.isAnswerSelected.observe(viewLifecycleOwner) { isSelected ->
            with(binding.btnNext) {
                visibility = if (isSelected) View.VISIBLE else View.GONE
                if (isSelected) {
                    if (viewModel.isGameFinished.value == true) {
                        visibility = View.VISIBLE
                        navigateToResultIfGameFinished()
                    }
                }
            }
        }
    }

    private fun navigateToResultIfGameFinished() {
        with(viewModel) {
            if (isGameFinished.value == true) {
                val correctAnswers = getCorrectAnswersCount()
                val incorrectAnswers = getIncorrectAnswersCount()
                val action = QuizFragmentDirections.
                actionQuizFragmentToResultFragment(
                    ResultViewArgs(
                        correctAnswers = correctAnswers,
                        incorrectAnswers = incorrectAnswers
                    )
                )
                findNavController().navigate(action)
            }
        }
    }

    companion object {
        private const val FLIPPER_POSITION_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_SUCCESS = 1
        private const val FLIPPER_CHILD_POSITION_ERROR = 2
    }
}