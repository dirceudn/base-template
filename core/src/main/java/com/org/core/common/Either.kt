package com.org.core.common

sealed class Either<out L, out R> {
    data class Left<out T>(val value: T) : Either<T, Nothing>()
    data class Right<out T>(val value: T) : Either<Nothing, T>()

    inline fun <X> map(f: (R) -> X): Either<L, X> = when (this) {
        is Right -> Right(f(value))
        is Left -> Left(value)
    }

    inline fun <X> fold(fl: (L) -> X, fr: (R) -> X): X = when (this) {
        is Right -> fr(value)
        is Left -> fl(value)
    }

    companion object {
        inline fun <T> catch(body: () -> T): Either<Throwable, T> = try {
            Right(body())
        } catch (t: Throwable) {
            Left(t)
        }
    }
}

inline fun <L, R, X> Either<L, R>.flatMap(f: (R) -> Either<L, X>): Either<L, X> = when (this) {
    is Either.Left -> this
    is Either.Right -> f(value)
}

fun <T> Either<T, T>.merge(): T = when (this) {
    is Either.Right -> value
    is Either.Left -> value
}

fun <L, R> Either<L, R>.getOrElse(default: R): R = when (this) {
    is Either.Right -> value
    is Either.Left -> default
}

inline fun <L, R, X> Either<L, R>.mapLeft(f: (L) -> X): Either<X, R> = when (this) {
    is Either.Right -> Either.Right(value)
    is Either.Left -> Either.Left(f(value))
}

inline fun <L, R> Either<L, R>.alsoRight(block: (R) -> Unit): Either<L, R>  {
    when (this) {
        is Either.Right -> block(this.value)
    }
    return this
}

inline fun <L, R> Either<L, R>.alsoLeft(block: (L) -> Unit): Either<L, R>  {
    when (this) {
        is Either.Left -> block(this.value)
    }
    return this
}
