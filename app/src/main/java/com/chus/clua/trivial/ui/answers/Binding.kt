package com.chus.clua.trivial.ui.answers

import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.chus.clua.trivial.R
import java.util.concurrent.TimeUnit

@BindingAdapter("html_text")
fun setHtmlTextFormat(textView: TextView, htmlText: String?) {
    textView.text =
        htmlText?.let { text -> HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY) }
}

@BindingAdapter("time_text")
fun setTimeText(textView: TextView, time: Long) {
    if (time <= 5000) textView.setTextColor(ContextCompat.getColor(textView.context, R.color.error))
    textView.text = "${TimeUnit.MILLISECONDS.toSeconds(time)}''"
}

@BindingAdapter("is_answer_correct")
fun setButtonBackground(button: Button, answerResult: AnswerResultWrapper?) {
    when {
        answerResult == null -> return
        answerResult.answer == button.tag && answerResult.result == AnswerResultWrapper.AnswerResult.OK -> {
            button.setTextColor(ContextCompat.getColor(button.context, R.color.white))
            button.backgroundTintList =
                ContextCompat.getColorStateList(button.context, R.color.teal_200)
        }
        answerResult.answer == button.tag && answerResult.result == AnswerResultWrapper.AnswerResult.KO -> {
            button.setTextColor(ContextCompat.getColor(button.context, R.color.white))
            button.backgroundTintList =
                ContextCompat.getColorStateList(button.context, R.color.error)
        }
        answerResult.correctAnswer == button.tag -> {
            button.setTextColor(ContextCompat.getColor(button.context, R.color.white))
            button.backgroundTintList =
                ContextCompat.getColorStateList(button.context, R.color.teal_200)
        }
    }
}

@BindingAdapter("run_anim")
fun runAnim(lottieAnimationView: LottieAnimationView, start: Boolean) {
    if (start) {
        lottieAnimationView.playAnimation()
    } else {
        lottieAnimationView.cancelAnimation()
    }
}




