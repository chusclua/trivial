package com.chus.clua.trivial.domain.mapper

import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.extensions.getScore
import com.chus.clua.trivial.domain.extensions.getShuffledAnswers
import com.chus.clua.trivial.ui.answers.QuestionUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionToUiQuestionMapper @Inject constructor() {
    fun mapUiModel(question: Question) =
        QuestionUi(
            score = question.getScore(),
            question = question.question,
            category = question.category,
            correctAnswer = question.correctAnswer,
            answers = question.getShuffledAnswers()
        )
}