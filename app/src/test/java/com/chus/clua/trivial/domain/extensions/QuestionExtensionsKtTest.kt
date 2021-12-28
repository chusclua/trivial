package com.chus.clua.trivial.domain.extensions

import com.chus.clua.trivial.domain.models.QuestionDifficulty
import com.chus.clua.trivial.utils.question
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class QuestionExtensionsKtTest {

    @Test
    fun `WHEN getScore from Question THEN returns the correct value associated`() {
        assertEquals(25, question.getScore())
        assertEquals(10, question.copy(difficulty = QuestionDifficulty.MEDIUM).getScore())
        assertEquals(5, question.copy(difficulty = QuestionDifficulty.EASY).getScore())
        assertEquals(0, question.copy(difficulty = null).getScore())
    }

    @Test
    fun `WHEN getShuffledAnswers from Question THEN returns a suffled question list`() {
        val firstShuffledAnswers = question.getShuffledAnswers()
        val secondShuffledAnswers = question.getShuffledAnswers()
        assertNotEquals(firstShuffledAnswers, secondShuffledAnswers)
    }
}