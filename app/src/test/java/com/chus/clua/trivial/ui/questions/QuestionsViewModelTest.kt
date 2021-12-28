package com.chus.clua.trivial.ui.questions

import androidx.lifecycle.Observer
import com.chus.clua.trivial.base.BaseViewModelTest
import com.chus.clua.trivial.domain.Either
import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.usecases.GetGameScoreUseCase
import com.chus.clua.trivial.domain.usecases.GetNewQuestionUseCase
import com.chus.clua.trivial.ui.base.Event
import com.chus.clua.trivial.utils.question
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class QuestionsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var getGameScoreUseCase: GetGameScoreUseCase

    @Mock
    private lateinit var questionUseCase: GetNewQuestionUseCase

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Mock
    private lateinit var scoreObserver: Observer<Int>

    @Mock
    private lateinit var questionObserver: Observer<Event<Question>>

    private lateinit var viewModel: QuestionsViewModel

    @Before
    fun setUp() {
        viewModel = QuestionsViewModel(getGameScoreUseCase, questionUseCase)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.score.observeForever(scoreObserver)
        viewModel.question.observeForever(questionObserver)
    }

    @Test
    fun `WHEN QuestionsViewModel loadRecord THEN obtains a record value`() {
        whenever(getGameScoreUseCase()).thenReturn(35)

        viewModel.loadRecord()

        verify(scoreObserver).onChanged(35)
    }

    @Test
    fun `WHEN QuestionsViewModel loadNewQuestion THEN obtains a Question`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            whenever(questionUseCase()).thenReturn(Either.Right(question))

            viewModel.loadQuestion()

            verify(loadingObserver).onChanged(true)
            verify(errorObserver, never()).onChanged(any())
            verify(questionObserver).onChanged(Event(question))
            verify(loadingObserver).onChanged(false)

        }

    @Test
    fun `WHEN QuestionsViewModel loadNewQuestion THEN obtains an error message`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val exception = mock<Exception>()
            whenever(questionUseCase()).thenReturn(Either.Left(exception))

            viewModel.loadQuestion()

            verify(loadingObserver).onChanged(true)
            verify(errorObserver).onChanged(exception.message)
            verify(questionObserver, never()).onChanged(Event(question))
            verify(loadingObserver).onChanged(false)

        }

}