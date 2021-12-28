package com.chus.clua.trivial.domain.usecases

import com.chus.clua.trivial.domain.repositories.QuestionsRepository
import javax.inject.Inject

class GetNewQuestionUseCase @Inject constructor(private val questionsRepository: QuestionsRepository) {
    suspend operator fun invoke() = questionsRepository.getQuestion()
}