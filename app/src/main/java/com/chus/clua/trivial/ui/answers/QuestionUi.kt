package com.chus.clua.trivial.ui.answers

data class QuestionUi(
    val score: Int,
    val question: String?,
    val category: String?,
    val correctAnswer: String?,
    val answers: List<String>?
)
