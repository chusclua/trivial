package com.chus.clua.trivial.domain.mapper

import com.chus.clua.trivial.utils.question as questionModel
import org.junit.Assert.*
import org.junit.Test

class QuestionToUiQuestionMapperTest {

    private val mapper = QuestionToUiQuestionMapper()

    @Test
    fun `WHEN map a Domain model THEN obtains a QuestionUi`() {
        with(mapper.mapUiModel(questionModel)) {
            assertEquals(25, score)
            assertEquals("What is the name of the formerly rich fishing grounds off the island of Newfoundland, Canada?", question)
            assertEquals("Grand Banks", correctAnswer)
            assertEquals("Geography", category)
            assertNotEquals(questionModel.incorrectAnswers, answers)
        }
    }
}