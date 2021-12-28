package com.chus.clua.trivial.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chus.clua.trivial.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected var binding: VB? = null
    protected var navHostFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding?.root)
        initNavHostFragment()
        render()
    }

    protected abstract fun getViewBinding(): VB

    protected abstract fun render()

    protected fun showActionBar() {
        supportActionBar?.show()
    }

    protected fun hideActionBar() {
        supportActionBar?.hide()
    }

    protected fun showHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected fun hideHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initNavHostFragment() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

}