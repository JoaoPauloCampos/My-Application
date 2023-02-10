package com.jnicomedes.myapplication.data.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

abstract class UseCase<out T, in Params>(private val contextProvider: ThreadContextProvider) : KoinComponent {

    abstract suspend fun run(params: Params? = null): Either<T, Exception>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onError: ((Exception) -> Unit) = {},
        onSuccess: (T) -> Unit
    ) {
        scope.launch(contextProvider.io) {
            run(params).run {
                withContext(contextProvider.main) {
                    either(
                        {
                            onSuccess(it)
                        },
                        {
                            onError(it)
                        }
                    )
                }
            }
        }
    }
}