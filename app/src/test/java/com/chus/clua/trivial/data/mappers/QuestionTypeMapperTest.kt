package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.domain.models.QuestionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

class QuestionTypeMapperTest {

    private val booleanType = "boolean"
    private val multipleType = "multiple"
    private val mapper = QuestionTypeMapper()

    @Test
    fun `WHEN map a valid type THEN obtains a QuestionType`() {
        assertEquals(QuestionType.BOOLEAN,mapper.mapType(booleanType))
        assertEquals(QuestionType.MULTIPLE,mapper.mapType(multipleType))
    }

    @Test
    fun `WHEN map an invalid type THEN obtains a null`() {
        assertNull(mapper.mapType(UUID.randomUUID().toString()))
        assertNull(mapper.mapType(null))
    }
}