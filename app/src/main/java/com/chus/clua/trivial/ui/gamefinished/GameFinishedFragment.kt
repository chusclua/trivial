package com.chus.clua.trivial.ui.gamefinished

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.chus.clua.trivial.R
import com.chus.clua.trivial.databinding.FragmentGameFinishedBinding
import com.chus.clua.trivial.ui.base.BaseFragment
import com.chus.clua.trivial.ui.base.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFinishedFragment : BaseFragment<FragmentGameFinishedBinding>() {

    private val viewModel: GameFinishedViewModel by viewModels()
    override fun getViewBinding() = FragmentGameFinishedBinding.inflate(layoutInflater)

    private val action =
        GameFinishedFragmentDirections.actionGameFinishedFragmentToQuestionsFragment()

    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (navController.currentDestination?.id == R.id.gameFinishedFragment) {
                viewModel.finishGame()
            } else {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        observeViewModel()
    }

    override fun render() {
        viewModel.load()
    }

    private fun observeViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner, { result ->
            requireActivity().title =
                if (result.isNewRecord) getString(R.string.new_record_title) else getString(R.string.game_over_title)
        })
        viewModel.finishGame.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(action)
        })
    }

}