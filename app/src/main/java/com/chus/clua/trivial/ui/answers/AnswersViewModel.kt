package com.chus.clua.trivial.ui.answers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chus.clua.trivial.domain.mapper.QuestionToUiQuestionMapper
import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.usecases.AddScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val addScoreUseCase: AddScoreUseCase,
    private val mapper: QuestionToUiQuestionMapper,
    private val answerCountDownTimer: AnswerCountDownTimer
) : ViewModel() {

    init {
        answerCountDownTimer.onTick = { millisUntilFinished ->
            _time.postValue(millisUntilFinished)
        }
        answerCountDownTimer.onFinish = {
            onTimeEnded()
        }
    }

    private val _time = MutableLiveData<Long>().apply { value = AnswerCountDownTimer.QUESTION_TIME }
    val time: LiveData<Long> get() = _time

    private val _questionUi = MutableLiveData<QuestionUi>()
    val questionUi: LiveData<QuestionUi> get() = _questionUi

    private val _answerResult = MutableLiveData<AnswerResultWrapper>()
    val answerResult: LiveData<AnswerResultWrapper> get() = _answerResult

    fun load(question: Question) {
        answerCountDownTimer.startAnswerCountDownTimer()
        _questionUi.postValue(mapper.mapUiModel(question))
    }

    fun validate(answer: String, questionUi: QuestionUi) {
        when (answer == questionUi.correctAnswer) {
            true -> onCorrectAnswer(answer, questionUi)
            false -> onIncorrectAnswer(answer, questionUi)
        }
    }

    private fun onCorrectAnswer(answer: String, question: QuestionUi?) {
        val result = AnswerResultWrapper(
            AnswerResultWrapper.AnswerResult.OK,
            answer,
            question?.correctAnswer
        )
        _answerResult.postValue(result)
        question?.score?.let { score -> addScoreUseCase(score) }
        answerCountDownTimer.cancelAnswerCountDownTimer()
    }

    private fun onIncorrectAnswer(answer: String, question: QuestionUi?) {
        val result = AnswerResultWrapper(
            AnswerResultWrapper.AnswerResult.KO,
            answer,
            question?.correctAnswer
        )
        _answerResult.postValue(result)
        answerCountDownTimer.cancelAnswerCountDownTimer()
    }

    private fun onTimeEnded() {
        val result = AnswerResultWrapper(
            AnswerResultWrapper.AnswerResult.TIME_ENDED,
            questionUi.value?.correctAnswer,
            questionUi.value?.correctAnswer
        )
        _answerResult.postValue(result)
    }

    override fun onCleared() {
        super.onCleared()
        answerCountDownTimer.cancel()
    }
}