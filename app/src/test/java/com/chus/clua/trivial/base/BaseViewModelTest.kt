package com.chus.clua.trivial.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chus.clua.trivial.utils.CoroutinesTestRule
import org.junit.Rule

open class BaseViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
}