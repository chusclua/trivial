package com.chus.clua.trivial.data.network

import com.chus.clua.trivial.data.models.RemoteQuestionsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val PATH = "/api.php"

private const val AMOUNT_PARAM = "amount"

interface TriviaApi {

    @GET(PATH)
    suspend fun getQuestion(
        @Query(AMOUNT_PARAM) amount: Int? = 1
    ): Response<RemoteQuestionsResult>
}