package com.chus.clua.trivial.ui.questions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chus.clua.trivial.databinding.FragmentQuestionsBinding
import com.chus.clua.trivial.domain.models.Question
import com.chus.clua.trivial.domain.models.QuestionType
import com.chus.clua.trivial.ui.base.BaseFragment
import com.chus.clua.trivial.ui.base.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>() {

    private val viewModel: QuestionsViewModel by viewModels()
    override fun getViewBinding() = FragmentQuestionsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        observeViewModel()
    }

    override fun render() {
        viewModel.loadRecord()
    }

    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner, EventObserver { question ->
            navigateToQuestion(question)
        })
        viewModel.error.observe(viewLifecycleOwner, { error ->
            showToast(error)
        })
    }

    private fun navigateToQuestion(question: Question) {
        val action = when (question.type) {
            QuestionType.MULTIPLE ->
                QuestionsFragmentDirections.actionQuestionsFragmentToMultipleAnswerFragmentFragment(
                    question
                )
            QuestionType.BOOLEAN ->
                QuestionsFragmentDirections.actionQuestionsFragmentToBooleanAnswersFragment(
                    question
                )
            null -> null
        }
        action?.let { act -> navController.navigate(act) }
    }

}