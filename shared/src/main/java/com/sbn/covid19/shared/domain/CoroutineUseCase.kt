package com.sbn.covid19.shared.domain

import android.util.Log
import com.sbn.covid19.shared.result.Result
import com.sbn.covid19.shared.result.Status
import com.sbn.covid19.shared.result.AppException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: AppException) {
            e.message?.let { Log.e(UseCase::class.simpleName, it) }
            if (e.status == Status.NON_TRACEABLE){
                Result.Error.NonRecoverableError(e)
            }else{
                Result.Error.RecoverableError(e)
            }

        } catch (e: Exception) {
            e.message?.let { Log.e(UseCase::class.simpleName, it) }
            Result.Error.NonRecoverableError(AppException("Constants.UNKNOWN_ERROR"))
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
