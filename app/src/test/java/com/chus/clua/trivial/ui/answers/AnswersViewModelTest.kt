package com.chus.clua.trivial.ui.answers

import androidx.lifecycle.Observer
import com.chus.clua.trivial.base.BaseViewModelTest
import com.chus.clua.trivial.domain.mapper.QuestionToUiQuestionMapper
import com.chus.clua.trivial.domain.usecases.AddScoreUseCase
import com.chus.clua.trivial.utils.question
import com.chus.clua.trivial.utils.questionUi
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnswersViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var addScoreUseCase: AddScoreUseCase

    @Mock
    private lateinit var mapper: QuestionToUiQuestionMapper

    @Mock
    private lateinit var timer: AnswerCountDownTimer

    @Mock
    private lateinit var answerObserver: Observer<AnswerResultWrapper>

    @Mock
    private lateinit var questionObserver: Observer<QuestionUi>

    @Mock
    private lateinit var timeObserver: Observer<Long>

    private lateinit var viewModel: AnswersViewModel

    @Before
    fun setUp() {
        viewModel = AnswersViewModel(addScoreUseCase, mapper, timer)
        viewModel.answerResult.observeForever(answerObserver)
        viewModel.questionUi.observeForever(questionObserver)
        viewModel.time.observeForever(timeObserver)
    }

    @Test
    fun `WHEN AnswersViewModel load THEN obtains a QuestionUi`() {
        whenever(mapper.mapUiModel(question)).thenReturn(questionUi)

        viewModel.load(question)

        verify(timer, times(1)).startAnswerCountDownTimer()
        verify(questionObserver).onChanged(questionUi)
        verify(timeObserver).onChanged(20000)

    }

    @Test
    fun `WHEN AnswersViewModel validate THEN obtains an AnswerResultOK`() {
        viewModel.validate(questionUi.correctAnswer!!, questionUi)

        verify(timer, times(1)).cancelAnswerCountDownTimer()
        verify(answerObserver).onChanged(
            AnswerResultWrapper(
                AnswerResultWrapper.AnswerResult.OK,
                questionUi.correctAnswer!!,
                questionUi.correctAnswer
            )
        )
        verify(addScoreUseCase, times(1)).invoke(questionUi.score)

    }

    @Test
    fun `WHEN AnswersViewModel validate THEN obtains an AnswerResultKO`() {
        val incorrectAnswer = "any answer"

        viewModel.validate(incorrectAnswer, questionUi)

        verify(timer, times(1)).cancelAnswerCountDownTimer()
        verify(answerObserver).onChanged(
            AnswerResultWrapper(
                AnswerResultWrapper.AnswerResult.KO,
                incorrectAnswer,
                questionUi.correctAnswer
            )
        )
        verify(addScoreUseCase, never()).invoke(any())

    }

}