package com.chus.clua.trivial.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val category: String?,
    val type: QuestionType?,
    val difficulty: QuestionDifficulty?,
    val question: String?,
    val correctAnswer: String?,
    val incorrectAnswers: List<String>?
): Parcelable
