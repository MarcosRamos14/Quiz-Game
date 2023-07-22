package com.example.core.usecase

import com.example.core.data.repository.QuestionAnswerRepository
import com.example.core.domain.model.AnswerResultDTO
import com.example.core.usecase.PostAnswerUseCase.PostAnswerUseCaseParams
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostAnswerUseCase {

    operator fun invoke(params: PostAnswerUseCaseParams) : Flow<ResultStatus<AnswerResultDTO>>

    data class PostAnswerUseCaseParams(val questionId: String, val answer: String)
}

class PostAnswerUseCaseImpl @Inject constructor(
    private val repository: QuestionAnswerRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<PostAnswerUseCaseParams, AnswerResultDTO>(), PostAnswerUseCase {

    override suspend fun doWork(params: PostAnswerUseCaseParams): ResultStatus<AnswerResultDTO> {
        return withContext(dispatchers.io()) {
            val submitAnswer = repository.postAnswer(params.questionId, params.answer)
            ResultStatus.Success(submitAnswer)
        }
    }
}