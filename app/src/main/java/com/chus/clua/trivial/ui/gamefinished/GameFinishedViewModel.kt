package com.chus.clua.trivial.ui.gamefinished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chus.clua.trivial.domain.usecases.*
import com.chus.clua.trivial.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFinishedViewModel @Inject constructor(
    private val getGameScoreUseCase: GetGameScoreUseCase,
    private val getQuestionAnsweredUseCase: GetQuestionAnsweredUseCase,
    private val isNewRecordUseCase: IsNewRecordUseCase,
    private val saveNewRecordUseCase: SaveNewRecordUseCase,
    private val finishGameUseCase: FinishGameUseCase
) : ViewModel() {

    private val _finishGame = MutableLiveData<Event<Int>>()
    val finishGame: LiveData<Event<Int>> get() = _finishGame

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> get() = _gameResult

    fun load() {
        val gameResult =
            mapResult(getGameScoreUseCase(), getQuestionAnsweredUseCase(), isNewRecordUseCase())
        _gameResult.postValue(gameResult)
        if (gameResult.isNewRecord) saveNewRecordUseCase()
    }

    fun finishGame() {
        finishGameUseCase()
        _finishGame.postValue(Event(0))
    }

    private fun mapResult(score: Int, questions: Int, isNewRecord: Boolean) =
        GameResult(score, questions, isNewRecord)

}