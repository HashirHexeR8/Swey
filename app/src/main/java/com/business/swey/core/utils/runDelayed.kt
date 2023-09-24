package com.business.swey.core.utils

import kotlinx.coroutines.*

inline fun runDelayed(
    delayInMillis: Long,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    crossinline block: () -> Unit
) {
    coroutineScope.launch {
        withContext(Dispatchers.IO) {
            delay(delayInMillis)
        }
        withContext(Dispatchers.Main) {
            block()
        }
    }
}