package com.chus.clua.trivial.ui.answers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.chus.clua.trivial.databinding.FragmentMultipleAnswersBinding
import com.chus.clua.trivial.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MultipleAnswersFragment : BaseFragment<FragmentMultipleAnswersBinding>() {

    private val args: MultipleAnswersFragmentArgs by navArgs()
    private val viewModel: AnswersViewModel by viewModels()
    override fun getViewBinding() = FragmentMultipleAnswersBinding.inflate(layoutInflater)

    private val action =
        MultipleAnswersFragmentDirections.actionMultipleAnswerFragmentFragmentToGameFinishedFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        observeViewModel()
    }

    override fun render() {
        args.question?.let { question ->
            viewModel.load(question)
            requireActivity().title = question.category
        }
    }

    private fun observeViewModel() {
        viewModel.answerResult.observe(viewLifecycleOwner, { answerResult ->
            when (answerResult.result) {
                AnswerResultWrapper.AnswerResult.OK -> onCorrectAnswer()
                AnswerResultWrapper.AnswerResult.KO, AnswerResultWrapper.AnswerResult.TIME_ENDED -> onIncorrectAnswer()
            }
        })
    }

    private fun onCorrectAnswer() {
        lifecycleScope.launch {
            delay(DELAY)
            navController.popBackStack()
        }
    }

    private fun onIncorrectAnswer() {
        lifecycleScope.launch {
            delay(DELAY)
            navController.navigate(action)
        }
    }

    companion object {
        private const val DELAY = 2000L
    }

}