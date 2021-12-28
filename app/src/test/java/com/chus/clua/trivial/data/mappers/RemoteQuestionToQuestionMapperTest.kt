package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.domain.models.QuestionDifficulty
import com.chus.clua.trivial.domain.models.QuestionType
import com.chus.clua.trivial.utils.remoteQuestionsResult
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteQuestionToQuestionMapperTest {

    @Mock
    private lateinit var typeMapper: QuestionTypeMapper

    @Mock
    private lateinit var difficultyMapper: QuestionDifficultyMapper
    private lateinit var mapper: RemoteQuestionToQuestionMapper

    @Before
    fun setUp() {
        mapper = RemoteQuestionToQuestionMapper(typeMapper, difficultyMapper)
    }

    @Test
    fun `WHEN map a remote model THEN obtains a Question`() {
        whenever(typeMapper.mapType("multiple")).thenReturn(QuestionType.MULTIPLE)
        whenever(difficultyMapper.mapDifficulty("hard")).thenReturn(QuestionDifficulty.HARD)
        with(mapper.mapFromRemote(remoteQuestionsResult.results!!.first())) {
            assertEquals("Geography", category)
            assertEquals(QuestionType.MULTIPLE, type)
            assertEquals(QuestionDifficulty.HARD, difficulty)
            assertEquals(
                "What is the name of the formerly rich fishing grounds off the island of Newfoundland, Canada?",
                question
            )
            assertEquals("Grand Banks", correctAnswer)
            assertEquals(
                listOf(
                    "Great Barrier Reef",
                    "Mariana Trench",
                    "Hudson Bay"
                ), incorrectAnswers
            )
        }
    }
}