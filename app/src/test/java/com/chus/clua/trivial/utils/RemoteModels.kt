package com.chus.clua.trivial.utils

import com.chus.clua.trivial.data.models.RemoteQuestion
import com.chus.clua.trivial.data.models.RemoteQuestionsResult

val remoteQuestion = RemoteQuestion(
    "Geography",
    "multiple",
    "hard",
    "What is the name of the formerly rich fishing grounds off the island of Newfoundland, Canada?",
    "Grand Banks",
    listOf(
        "Great Barrier Reef",
        "Mariana Trench",
        "Hudson Bay"
    )
)

val remoteQuestionsResult = RemoteQuestionsResult(
    0,
    listOf(remoteQuestion)
)