package com.chus.clua.trivial.domain.extensions

import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.models.QuestionDifficulty

fun Question.getScore(): Int {
    return when (this.difficulty) {
        QuestionDifficulty.EASY -> 5
        QuestionDifficulty.MEDIUM -> 10
        QuestionDifficulty.HARD -> 25
        else -> 0
    }
}

fun Question.getShuffledAnswers() =
    this.incorrectAnswers?.plus(listOfNotNull(this.correctAnswer))?.shuffled()