package com.sbn.covid19.shared.data

import androidx.annotation.WorkerThread
import com.sbn.covid19.shared.result.AppException
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.result.Status
import com.sbn.covid19.shared.util.NetworkUtils
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException

abstract class RemoteRepository constructor(private val networkUtils: NetworkUtils) {

    @WorkerThread
    suspend fun <T : Any> execute(
        call: suspend () -> Response<T>
    ): Result<T> {
        return if (networkUtils.hasNetworkConnection()) try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error.NonRecoverableError(AppException(response.message()))
            }

        } catch (se3: SocketTimeoutException) {
            Result.Error.NonRecoverableError(AppException("Sever not responding",Status.TIME_OUT))
        } catch (e: Exception) {
            Result.Error.NonRecoverableError(AppException("Something went worng",Status.NON_TRACEABLE))

        }
        else Result.Error.NonRecoverableError(AppException("No Internet connectivity", Status.NO_INTERNET))
    }
}