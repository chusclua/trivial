package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.domain.models.QuestionDifficulty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionDifficultyMapper @Inject constructor() {
    fun mapDifficulty(difficulty: String?): QuestionDifficulty? {
        return try {
            QuestionDifficulty.valueOf(difficulty!!.uppercase())
        } catch (e: IllegalArgumentException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }
}