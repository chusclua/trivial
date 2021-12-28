package com.chus.clua.trivial.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import com.chus.clua.trivial.databinding.FragmentSplashBinding
import com.chus.clua.trivial.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding() = FragmentSplashBinding.inflate(layoutInflater)

    private val action =
        SplashFragmentDirections.actionSplashFragmentToQuestionsFragment()

    override fun render() {
        startAnimation()
    }

    private fun startAnimation() {
        binding?.imgSplash
            ?.animate()
            ?.alpha(1f)
            ?.scaleXBy(1f)
            ?.scaleYBy(1f)
            ?.setDuration(3000)
            ?.setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    navController.navigate(action)
                }
            })
    }

}