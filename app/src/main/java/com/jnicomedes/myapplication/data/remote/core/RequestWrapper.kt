package com.jnicomedes.myapplication.data.remote.core

import com.jnicomedes.myapplication.data.domain.core.Either

suspend fun <D> requestWrapper(
    call: suspend () -> D
): Either<D, Exception> {
    return try {
        val data = call()
        Either.Success(data)
    } catch (ex: Exception) {
        Either.Failure(ex)
    }
}