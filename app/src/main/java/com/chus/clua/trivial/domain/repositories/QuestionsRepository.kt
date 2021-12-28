package com.chus.clua.trivial.domain.repositories

import com.chus.clua.trivial.domain.Either
import com.chus.clua.trivial.domain.models.Question

interface QuestionsRepository {
    suspend fun getQuestion(): Either<Exception, Question>
}