package com.chus.clua.trivial.ui.answers

data class AnswerResultWrapper(
    val result: AnswerResult,
    val answer: String?,
    val correctAnswer: String?
) {
    enum class AnswerResult { OK, KO, TIME_ENDED }
}