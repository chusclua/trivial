package com.chus.clua.trivial.utils

import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.models.QuestionDifficulty
import com.chus.clua.trivial.domain.models.QuestionType

val question = Question(
    "Geography",
    QuestionType.MULTIPLE,
    QuestionDifficulty.HARD,
    "What is the name of the formerly rich fishing grounds off the island of Newfoundland, Canada?",
    "Grand Banks",
    listOf(
        "Great Barrier Reef",
        "Mariana Trench",
        "Hudson Bay"
    )
)