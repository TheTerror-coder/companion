package ft.project.companion.data.datasource.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.data.di.IoDispatcher
import ft.project.companion.core.CompanionLogger
import ft.project.companion.data.di.CompanionAppScope
import ft.project.companion.domain.result.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import net.openid.appauth.AuthState
import org.json.JSONException
import javax.inject.Inject

private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
private val AUTH_STATE_KEY = stringPreferencesKey("auth_state_json")

class AuthDataStoreImpl @Inject constructor(
    private val errorManager: CompanionErrorManager,
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @CompanionAppScope private val externalScope: CoroutineScope,
    ): AuthDataStore {

    private val _dataStoreMutex = Mutex()

    init {
        Log.i(
            TAG,
            "************** AuthDataStoreImpl instance Initialisation **************"
        )
    }

    override suspend fun saveAuthState(authState: AuthState){

        externalScope.launch {

            try {
                CompanionLogger.i(
                    msg = "saving authState to the DataStore \n" +
                            "---> isAuthorized = ${authState.isAuthorized}\n" +
                            "---> accessToken = ${authState.accessToken}\n" +
                            "---> refreshToken = ${authState.refreshToken}\n" +
                            "---> needsTokenRefresh = ${authState.needsTokenRefresh}\n" +
                            "---> accessTokenExpirationTime = ${authState.accessTokenExpirationTime}\n" +
                            "---> clientSecret = ${authState.clientSecret}\n" +
                            "---> clientSecretExpirationTime = ${authState.clientSecretExpirationTime}\n" +
                            "---> idToken = ${authState.idToken}\n" +
                            "---> parsedIdToken = ${authState.parsedIdToken}"
                )
                val token: String? = authState.accessToken
                val serialized: String = authState.jsonSerializeString()

                context.companionDataStore.edit { currentPreferences ->
                    _dataStoreMutex.withLock {
                        currentPreferences[AUTH_STATE_KEY] = serialized
                        if (token != null){
                            currentPreferences[ACCESS_TOKEN_KEY] = token
                        }
                    }
                }
            } catch (e: IOException){
                val msg = "IOException: Data writing failure while saving authentication state on disk"
                CompanionLogger.e(
                    msg = "Error: $msg\n" +
                            "Caught IOException: ${e.localizedMessage}\n"
                )
                errorManager.emitError(msg = msg)
            } catch (e: CancellationException){
                CompanionLogger.d(
                    msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
                )
                throw e
            } catch (e: Exception){
                CompanionLogger.logException(
                    e = e,
                    errorManager = errorManager,
                    msg = "Exception: Data writing failure while saving authentication state on disk"
                )
            }
        }.join()
    }

    override suspend fun fetchAuthState(): Result<AuthState>{
        try {

            val stateJsonStr: String? = withContext(ioDispatcher){
                context.companionDataStore.data.map { currentPreferences ->
                    currentPreferences[AUTH_STATE_KEY]
                }
                    .firstOrNull()
            }

            if (stateJsonStr != null){
                return Result.Success(
                    AuthState.jsonDeserialize(stateJsonStr)
                )
            }
            return Result.Error
        } catch (e: JSONException){
                val msg = "json deserialisation failure while fetching authentication state from disk"
            CompanionLogger.e(
                msg = "Error: $msg\n" +
                        "Caught JSONException: ${e.localizedMessage}\n",
            )
            errorManager.emitError(msg = msg)
        } catch (e: CancellationException){
            CompanionLogger.d(
                msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
            )
            throw e
        } catch (e: Exception){
            CompanionLogger.logException(
                e = e,
                errorManager = errorManager,
                msg = "something went wrong while fetching authentication state from disk"
            )
        }
        return Result.Error
    }

    suspend fun fetchAccessToken(): Result<String> {
        try {
            val tokenStr: String? = withContext(ioDispatcher) {

                context.companionDataStore.data.map { currentPreferences ->
                    currentPreferences[ACCESS_TOKEN_KEY]
                }
                    .firstOrNull()
            }


            return if (tokenStr != null) Result.Success(tokenStr) else Result.Error
        } catch (e: CancellationException){
            CompanionLogger.d(
                msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
            )
            throw e
        } catch (e: Exception){
            CompanionLogger.logException(
                e = e,
                errorManager = errorManager,
                msg = "something went wrong while fetching access token from disk"
            )
        }
        return Result.Error
    }

}