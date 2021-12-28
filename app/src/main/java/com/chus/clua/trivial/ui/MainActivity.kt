package com.chus.clua.trivial.ui

import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.chus.clua.trivial.R
import com.chus.clua.trivial.databinding.ActivityMainBinding
import com.chus.clua.trivial.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun render() = initNavHostListener()

    private fun initNavHostListener() {
        navHostFragment?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> hideActionBar()
                R.id.questionsFragment -> {
                    showActionBar()
                    hideHomeButton()
                    title = getString(R.string.app_name)
                }
                R.id.booleanAnswersFragment,
                R.id.multipleAnswerFragmentFragment -> {
                    showActionBar()
                    showHomeButton()
                }
                R.id.gameFinishedFragment -> {
                    showActionBar()
                    hideHomeButton()
                }
            }
        }
    }
}