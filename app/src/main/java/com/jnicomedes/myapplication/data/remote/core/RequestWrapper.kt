package com.jnicomedes.myapplication.data.remote.core

import com.jnicomedes.myapplication.data.domain.core.Either

interface RequestWrapper {
    suspend fun <D> wrapper(call: suspend () -> D): Either<D, Exception>
}