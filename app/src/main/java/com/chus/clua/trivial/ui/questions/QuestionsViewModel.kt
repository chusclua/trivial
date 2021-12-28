package com.chus.clua.trivial.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chus.clua.trivial.domain.fold
import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.usecases.GetGameScoreUseCase
import com.chus.clua.trivial.domain.usecases.GetNewQuestionUseCase
import com.chus.clua.trivial.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getGameScoreUseCase: GetGameScoreUseCase,
    private val getNewQuestionUseCase: GetNewQuestionUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _question = MutableLiveData<Event<Question>>()
    val question: LiveData<Event<Question>> get() = _question

    fun loadRecord() {
        _score.postValue(getGameScoreUseCase())
    }

    fun loadQuestion() {
        viewModelScope.launch {
            _loading.postValue(true)
            getNewQuestionUseCase().fold(
                leftOp = { exception ->
                    _error.postValue(exception.message)
                    _loading.postValue(false)
                },
                rightOp = { question ->
                    _question.postValue(Event(question))
                    _loading.postValue(false)
                }
            )
        }
    }
}