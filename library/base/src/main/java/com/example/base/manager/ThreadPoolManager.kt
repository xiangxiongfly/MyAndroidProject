package com.example.base.manager

import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object ThreadPoolManager : ThreadPoolExecutor(
    0, 200,
    30L, TimeUnit.MILLISECONDS,
    SynchronousQueue()
)