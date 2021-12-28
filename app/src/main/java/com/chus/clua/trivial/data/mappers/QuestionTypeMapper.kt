package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.domain.models.QuestionType
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionTypeMapper @Inject constructor() {
    fun mapType(type: String?): QuestionType? {
        return try {
            QuestionType.valueOf(type!!.uppercase())
        } catch (e: IllegalArgumentException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }
}