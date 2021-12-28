package com.chus.clua.trivial.data.models

data class RemoteQuestionsResult(
    val response_code: Int?,
    val results: List<RemoteQuestion>?
)