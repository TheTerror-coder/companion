package ft.project.companion.data.remote.network

import android.util.Log
import ft.project.companion.TAG
import okhttp3.Interceptor
import okhttp3.Response

class LoggingOkHttpInterceptor: Interceptor {

    @Override
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.i(TAG, "Sending request: " +
                "${request.url} \n" +
                "${chain.connection()} \n" +
                "${request.headers} "
        )
        Log.d(TAG, "Authorization: ${request.header("Authorization")}")

        val response = chain.proceed(request)

        Log.i(TAG, "Received response: " +
                "${response.request.url} \n" +
                "${chain.connection()} \n" +
                "${response.headers} \n" +
                "status: ${response.code} "
        )

        return response
    }
}