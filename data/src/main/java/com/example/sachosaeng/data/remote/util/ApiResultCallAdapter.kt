package com.sachosaeng.app.data.remote.util

import com.example.sachosaeng.core.util.ErrorNotifier
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

internal class ApiResultCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<ApiResult<R>>> {

    override fun adapt(call: Call<R>): Call<ApiResult<R>> = ApiResultCall(call, successType)
    override fun responseType(): Type = successType
}

private class ApiResultCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<ApiResult<R>> {

    override fun enqueue(callback: Callback<ApiResult<R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                try {
                    val apiResult = response.toApiResult()
                    callback.onResponse(this@ApiResultCall, Response.success(apiResult))
                } catch (e: Exception) {
                    val errorResult = ApiResult.Failure.UnknownApiError(e)
                    callback.onResponse(this@ApiResultCall, Response.success(errorResult))
                }
            }

            private fun Response<R>.toApiResult(): ApiResult<R> {
                if (!isSuccessful) {
                    val errorBody = try {
                        errorBody()?.string() ?: "Unknown error body"
                    } catch (e: Exception) {
                        "Failed to read error body"
                    }
                    return ApiResult.Failure.HttpError(
                        code = code(),
                        message = message(),
                        body = errorBody
                    )
                }

                body()?.let {
                    return ApiResult.successOf(it)
                } ?: run {
                    return if (successType == Unit::class.java) {
                        @Suppress("UNCHECKED_CAST")
                        ApiResult.successOf(Unit as R)
                    } else {
                        ApiResult.Failure.UnknownApiError(
                            IllegalStateException("Response body is null")
                        )
                    }
                }

                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    ApiResult.successOf(Unit as R)
                } else {
                    ApiResult.Failure.UnknownApiError(
                        IllegalStateException(
                            "Response code is ${code()} but body is null.\n" +
                                    "If you expect response body to be null then define your API method as returning Unit:\n" +
                                    "@POST fun postSomething(): ApiResult<Unit>"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<R?>, throwable: Throwable) {
                val error = if (throwable is IOException) {
                    ApiResult.Failure.NetworkError(throwable)
                } else {
                    ApiResult.Failure.UnknownApiError(throwable)
                }
                ErrorNotifier.notifyError(throwable)
                callback.onResponse(this@ApiResultCall, Response.success(error))
            }
        }
    )

    override fun timeout(): Timeout = delegate.timeout()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<ApiResult<R>> = ApiResultCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiResult<R>> =
        throw UnsupportedOperationException("This adapter does not support sync execution")

    override fun request(): Request = delegate.request()
}