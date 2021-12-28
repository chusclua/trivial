package com.chus.clua.trivial.data.network

import com.chus.clua.trivial.data.utils.callHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TriviaApiClient @Inject constructor(private val triviaApi: TriviaApi) {
    suspend fun getQuestion() = callHandler {
        triviaApi.getQuestion()
    }
}