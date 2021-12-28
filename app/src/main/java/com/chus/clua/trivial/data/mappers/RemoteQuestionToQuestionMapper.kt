package com.chus.clua.trivial.data.mappers

import com.chus.clua.trivial.data.models.RemoteQuestion
import com.chus.clua.trivial.domain.models.Question
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteQuestionToQuestionMapper @Inject constructor(
    private val questionTypeMapper: QuestionTypeMapper,
    private val questionDifficultyMapper: QuestionDifficultyMapper
) {
    fun mapFromRemote(remote: RemoteQuestion) =
        with(remote) {
            Question(
                remote.category,
                questionTypeMapper.mapType(type),
                questionDifficultyMapper.mapDifficulty(difficulty),
                question,
                correct_answer,
                incorrect_answers
            )
        }
}