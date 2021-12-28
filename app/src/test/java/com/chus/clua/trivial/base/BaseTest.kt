package com.chus.clua.trivial.base

import kotlinx.coroutines.test.TestCoroutineDispatcher

open class BaseTest {
    val testCoroutineDispatcher by lazy { TestCoroutineDispatcher() }
}