package com.example.quizgame.ui.quiz

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.AnswerDTO
import com.example.core.domain.model.QuestionDTO
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding
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

        quizAdapter.submitList(
            listOf(
                QuizAdapterItem.QuizQuestion(QuestionDTO(0, "We achieved ?"), true),
                QuizAdapterItem.QuizAnswer(AnswerDTO(1, "Question 1")),
                QuizAdapterItem.QuizAnswer(AnswerDTO(1, "Question 2")),
                QuizAdapterItem.QuizAnswer(AnswerDTO(1, "Question 3")),
                QuizAdapterItem.QuizAnswer(AnswerDTO(1, "Question 4"))
            )
        )
    }
}