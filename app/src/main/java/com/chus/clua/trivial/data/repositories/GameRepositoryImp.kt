package com.chus.clua.trivial.data.repositories

import com.chus.clua.trivial.data.local.PreferencesKey
import com.chus.clua.trivial.data.local.SharedPreferencesHelper
import com.chus.clua.trivial.domain.repositories.GameRepository

class GameRepositoryImp(
    private var questionsAnswered: Int = 0,
    private var currentScore: Int = 0,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : GameRepository {

    override fun putRecord() {
        sharedPreferencesHelper.put(PreferencesKey.SCORE, currentScore)
    }

    override fun getRecord(): Int = sharedPreferencesHelper.get(PreferencesKey.SCORE)

    override fun addScore(score: Int) {
        questionsAnswered = questionsAnswered.inc()
        currentScore = currentScore.plus(score)
    }

    override fun getCurrentScore() = currentScore

    override fun getQuestionsAnswered() = questionsAnswered

    override fun finishGame() {
        questionsAnswered = 0
        currentScore = 0
    }
}