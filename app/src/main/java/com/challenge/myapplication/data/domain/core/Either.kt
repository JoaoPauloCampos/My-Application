package com.challenge.myapplication.data.domain.core

sealed class Either<out S, out F> {
    data class Failure<out F>(val value: F) : Either<Nothing, F>()
    data class Success<out S>(val value: S) : Either<S, Nothing>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>

    fun <L> failure(a: L) = Failure(a)
    fun <R> success(b: R) = Success(b)

    fun either(fnR: (S) -> Any, fnL: (F) -> Any): Any =
        when (this) {
            is Failure -> fnL(value)
            is Success -> fnR(value)
        }
}