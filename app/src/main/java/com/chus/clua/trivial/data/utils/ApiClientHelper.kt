package com.chus.clua.trivial.data.utils

import com.chus.clua.trivial.domain.Either
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

suspend fun <T> callHandler(call: suspend () -> Response<T>): Either<Exception, T> {
    return try {
        val response = call()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            Either.Right(responseBody)
        } else {
            Either.Left(Exception(response.message()))
        }
    } catch (httpException: HttpException) {
        Either.Left(Exception(httpException.message()))
    } catch (e: Throwable) {
        Either.Left(Exception(e.message))
    }
}