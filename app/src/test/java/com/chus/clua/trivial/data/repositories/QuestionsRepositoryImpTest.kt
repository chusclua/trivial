package com.chus.clua.trivial.data.repositories

import com.chus.clua.trivial.base.BaseTest
import com.chus.clua.trivial.data.mappers.RemoteQuestionToQuestionMapper
import com.chus.clua.trivial.data.network.TriviaApiClient
import com.chus.clua.trivial.domain.*
import com.chus.clua.trivial.domain.repositories.QuestionsRepository
import com.chus.clua.trivial.utils.remoteQuestionsResult
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.chus.clua.trivial.utils.question
import com.nhaarman.mockitokotlin2.mock

@RunWith(MockitoJUnitRunner::class)
class QuestionsRepositoryImpTest : BaseTest() {

    private lateinit var repository: QuestionsRepository

    @Mock
    private lateinit var apiClient: TriviaApiClient

    @Mock
    private lateinit var mapper: RemoteQuestionToQuestionMapper

    @Before
    fun setUp() {
        repository = QuestionsRepositoryImp(apiClient, mapper, testCoroutineDispatcher)
    }

    @Test
    fun `WHEN getQuestion THEN returns an Either Right`() =
        testCoroutineDispatcher.runBlockingTest {
            whenever(apiClient.getQuestion()).thenReturn(Either.Right(remoteQuestionsResult))
            whenever(mapper.mapFromRemote(remoteQuestionsResult.results!![0])).thenReturn(question)

            val result = repository.getQuestion()

            assertTrue(result.isRight)
            assertEquals(question, result.data)
        }

    @Test
    fun `WHEN getQuestion and Api returns an exception THEN returns an Either Left`() =
        testCoroutineDispatcher.runBlockingTest {
            val exception = mock<Exception>()
            whenever(apiClient.getQuestion()).thenReturn(Either.Left(exception))

            val result = repository.getQuestion()

            assertTrue(result.isLeft)
            assertEquals(exception.message, result.error.message)
        }

    @Test
    fun `WHEN getQuestion and Api returns an empty list THEN returns an Either Left`() =
        testCoroutineDispatcher.runBlockingTest {
            whenever(apiClient.getQuestion()).thenReturn(
                Either.Right(remoteQuestionsResult.copy(results = emptyList()))
            )

            val result = repository.getQuestion()

            assertTrue(result.isLeft)
            assertEquals("Empty body", result.error.message)
        }
}