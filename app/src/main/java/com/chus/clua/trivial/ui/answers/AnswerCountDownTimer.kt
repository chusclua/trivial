package com.chus.clua.trivial.ui.answers

import android.os.CountDownTimer
import javax.inject.Inject

class AnswerCountDownTimer @Inject constructor() : CountDownTimer(QUESTION_TIME, INTERVAL) {

    private var started = false

    var onTick: ((millisUntilFinished: Long) -> Unit)? = null
    var onFinish: (() -> Unit)? = null

    fun startAnswerCountDownTimer() {
        if (!started) {
            super.start()
            started = true
        }
    }

    fun cancelAnswerCountDownTimer() {
        super.cancel()
        started = false
    }

    override fun onTick(millisUntilFinished: Long) {
        onTick?.invoke(millisUntilFinished)
    }

    override fun onFinish() {
        onFinish?.invoke()
        started = false
    }

    companion object {
        const val QUESTION_TIME = 20000L
        private const val INTERVAL = 1000L
    }

}