package ft.project.companion.core

import android.util.Log
import ft.project.companion.TAG
import net.openid.appauth.AuthorizationException

object CompanionLogger{

    private val _trace: String
        get() {
        val stackTraceElement = Throwable().stackTrace[3]
        return "------------ From File: ${stackTraceElement.fileName}\n" +
                "------------ in Class: ${stackTraceElement.className}\n" +
                "------------ in Method: ${stackTraceElement.methodName}\n" +
                "------------ at Line: ${stackTraceElement.lineNumber}\n"
    }

    fun e(
        tag: String = TAG,
        msg: String,
    ){
        Log.e(
            tag,
            msg + "\n$_trace"
        )
    }

    fun d(
        tag: String = TAG,
        msg: String,
    ){
        Log.d(
            tag,
            msg + "\n$_trace"
        )
    }

    fun i(
        tag: String = TAG,
        msg: String,
    ){
        Log.i(
            tag,
            msg + "\n$_trace"
        )
    }

    suspend fun logException(
        tag: String = TAG,
        e: Exception,
        errorManager: CompanionErrorManager,
        msg: String
    ){
        Log.e(
            tag,
            "Error: $msg\n" +
                    "Caught ${e.toString()}: ${e.localizedMessage}\n$_trace"
        )
        errorManager.emitError(msg = msg)
    }

    suspend fun logException(
        tag: String = TAG,
        e: AuthorizationException,
        errorManager: CompanionErrorManager,
        msg: String
    ){
        Log.e(
            tag,
            "Error: $msg\n" +
                    "Caught $e: ${e.localizedMessage}\n$_trace"
        )
        errorManager.emitError(msg = msg)
    }

    suspend fun logError(
        tag: String = TAG,
        errorManager: CompanionErrorManager,
        msg: String
    ){
        Log.e(
            tag,
            "Error: $msg\n$_trace"
        )
        errorManager.emitError(msg = msg)
    }
}
