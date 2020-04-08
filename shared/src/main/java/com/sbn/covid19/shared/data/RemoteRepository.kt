package com.sbn.covid19.shared.data

import androidx.annotation.WorkerThread
import com.sbn.covid19.shared.result.Result
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
                Result.Error(Exception(response.message()))
            }

        } catch (se3: SocketTimeoutException) {
            Result.Error(Exception("Sever not responding"))
        } catch (e: Exception) {
            Result.Error(e)

        }
        else Result.Error(Exception("No Internet connectivity"))
    }
}