package com.org.core.common

sealed class State<out T>(val data: T?) {

    class Uninitialized<out T>(data: T? = null) : State<T>(null)

    class Success<out T>(data: T?) : State<T>(data)

    class Failure<out T>(val error: DefaultErrorEntity? = null, data: T? = null) :
        State<T>(data)

    class Loading<out T>(data: T? = null) : State<T>(data)

    operator fun invoke(): T? = data

    val uninitialized: Boolean get() = this is Uninitialized
    val loading: Boolean get() = this is Loading
    val failed: Boolean get() = this is Failure
    val successful: Boolean get() = this is Success
    val complete: Boolean get() = this is Error || this is Success

    override fun toString(): String {
        return when (this) {
            is Uninitialized -> "Uninitialized"
            is Success -> "Success - data: $data"
            is Failure -> "Failure - data: $data"
            is Loading -> "Loading - data: $data"
        }
    }
}

fun <T, R> State<T>.map(transform: (T) -> R): State<R> {
    return when (this) {
        is State.Success -> data?.let { State.Success(transform(data)) }
            ?: State.Success<R>(null)
        is State.Uninitialized -> data?.let { State.Uninitialized(transform(data)) }
            ?: State.Uninitialized<R>(null)
        is State.Failure -> data?.let { State.Failure(error = error, data = transform(data)) }
            ?: State.Failure(null)
        is State.Loading -> data?.let { State.Loading(transform(data)) } ?: State.Loading<R>(null)
    }
}
