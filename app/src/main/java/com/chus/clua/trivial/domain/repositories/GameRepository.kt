package com.chus.clua.trivial.domain.repositories

interface GameRepository {
    fun putRecord()
    fun getRecord(): Int
    fun addScore(score: Int)
    fun getCurrentScore(): Int
    fun getQuestionsAnswered(): Int
    fun finishGame()
}