package com.chus.clua.trivial.ui.gamefinished

import androidx.lifecycle.Observer
import com.chus.clua.trivial.base.BaseViewModelTest
import com.chus.clua.trivial.domain.usecases.*
import com.chus.clua.trivial.ui.base.Event
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameFinishedViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var getGameScoreUseCase: GetGameScoreUseCase

    @Mock
    private lateinit var getQuestionAnsweredUseCase: GetQuestionAnsweredUseCase

    @Mock
    private lateinit var isNewRecordUseCase: IsNewRecordUseCase

    @Mock
    private lateinit var saveNewRecordUseCase: SaveNewRecordUseCase

    @Mock
    private lateinit var finishGameUseCase: FinishGameUseCase

    @Mock
    private lateinit var resultObserver: Observer<GameResult>

    @Mock
    private lateinit var finishObserver: Observer<Event<Int>>

    private lateinit var viewModel: GameFinishedViewModel

    @Before
    fun setUp() {
        viewModel = GameFinishedViewModel(
            getGameScoreUseCase,
            getQuestionAnsweredUseCase,
            isNewRecordUseCase,
            saveNewRecordUseCase,
            finishGameUseCase
        )
        viewModel.gameResult.observeForever(resultObserver)
        viewModel.finishGame.observeForever(finishObserver)
    }

    @Test
    fun `WHEN GameFinishedViewModel load THEN obtains a GameResult with new record`() {
        whenever(getGameScoreUseCase()).thenReturn(35)
        whenever(getQuestionAnsweredUseCase()).thenReturn(5)
        whenever(isNewRecordUseCase()).thenReturn(true)

        viewModel.load()

        verify(resultObserver).onChanged(GameResult(35, 5, true))
        verify(saveNewRecordUseCase).invoke()

    }

    @Test
    fun `WHEN GameFinishedViewModel load THEN obtains a GameResult without new record`() {
        whenever(getGameScoreUseCase()).thenReturn(30)
        whenever(getQuestionAnsweredUseCase()).thenReturn(6)
        whenever(isNewRecordUseCase()).thenReturn(false)

        viewModel.load()

        verify(resultObserver).onChanged(GameResult(30, 6, false))
        verify(saveNewRecordUseCase, never()).invoke()

    }

    @Test
    fun `WHEN GameFinishedViewModel load THEN obtains a GameResult with 0 points`() {
        whenever(getGameScoreUseCase()).thenReturn(0)
        whenever(getQuestionAnsweredUseCase()).thenReturn(1)
        whenever(isNewRecordUseCase()).thenReturn(false)

        viewModel.load()

        verify(resultObserver).onChanged(GameResult(0, 1, false))
        verify(saveNewRecordUseCase, never()).invoke()

    }

    @Test
    fun `WHEN GameFinishedViewModel finishGame THEN navigate to Main`() {

        viewModel.finishGame()

        verify(finishObserver).onChanged(Event(0))
        verify(finishGameUseCase).invoke()

    }
}