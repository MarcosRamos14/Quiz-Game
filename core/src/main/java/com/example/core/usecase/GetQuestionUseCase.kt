package com.example.core.usecase

import com.example.core.data.repository.QuestionAnswerRepository
import com.example.core.domain.model.QuestionDTO
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetQuestionUseCase {
    operator fun invoke(params: Unit = Unit) : Flow<ResultStatus<QuestionDTO>>
}

class GetQuestionUseCaseImpl @Inject constructor(
    private val repository: QuestionAnswerRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<Unit, QuestionDTO>(), GetQuestionUseCase {

    override suspend fun doWork(params: Unit): ResultStatus<QuestionDTO> {
        return withContext(dispatchers.io()) {
            val question = repository.getQuestion()
            ResultStatus.Success(question)
        }
    }
}