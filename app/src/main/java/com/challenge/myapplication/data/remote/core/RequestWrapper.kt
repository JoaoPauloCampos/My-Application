package com.challenge.myapplication.data.remote.core

import com.challenge.myapplication.data.domain.core.Either

interface RequestWrapper {
    suspend fun <D> wrapper(call: suspend () -> D): Either<D, Exception>
}