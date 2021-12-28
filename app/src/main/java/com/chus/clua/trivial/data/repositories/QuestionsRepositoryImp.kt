package com.chus.clua.trivial.data.repositories

import com.chus.clua.trivial.data.mappers.RemoteQuestionToQuestionMapper
import com.chus.clua.trivial.data.network.TriviaApiClient
import com.chus.clua.trivial.domain.Either
import com.chus.clua.trivial.domain.flatMap
import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.repositories.QuestionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionsRepositoryImp(
    private val apiClient: TriviaApiClient,
    private val mapper: RemoteQuestionToQuestionMapper,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : QuestionsRepository {
    override suspend fun getQuestion(): Either<Exception, Question> {
        return withContext(dispatcherIO) {
            apiClient.getQuestion().flatMap { result ->
                result.results?.firstOrNull()?.let { question ->
                    Either.Right(mapper.mapFromRemote(question))
                } ?: run {
                    Either.Left(Exception("Empty body"))
                }
            }
        }
    }

}