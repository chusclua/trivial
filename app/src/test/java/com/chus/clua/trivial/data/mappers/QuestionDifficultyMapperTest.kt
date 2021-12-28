package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.domain.models.QuestionDifficulty
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

class QuestionDifficultyMapperTest {

    private val easy = "easy"
    private val medium = "medium"
    private val hard = "hard"
    private val mapper = QuestionDifficultyMapper()

    @Test
    fun `WHEN map a valid difficult THEN obtains a QuestionDifficult`() {
        assertEquals(QuestionDifficulty.EASY,mapper.mapDifficulty(easy))
        assertEquals(QuestionDifficulty.MEDIUM,mapper.mapDifficulty(medium))
        assertEquals(QuestionDifficulty.HARD,mapper.mapDifficulty(hard))
    }

    @Test
    fun `WHEN map an invalid difficult THEN obtains a null`() {
        assertNull(mapper.mapDifficulty(UUID.randomUUID().toString()))
        assertNull(mapper.mapDifficulty(null))
    }

}