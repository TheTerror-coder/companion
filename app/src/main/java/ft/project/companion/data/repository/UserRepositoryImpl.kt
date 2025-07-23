package ft.project.companion.data.repository

import android.util.Log
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionLogger
import ft.project.companion.data.datasource.UserRemoteDataSource
import ft.project.companion.data.di.CompanionAppScope
import ft.project.companion.data.di.IoDispatcher
import ft.project.companion.domain.datasource.AuthDataStore
import ft.project.companion.domain.model.UserModel
import ft.project.companion.domain.repository.UserRepository
import ft.project.companion.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ClientSecretBasic
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val authDataStore: AuthDataStore,
    private val authorizationService: AuthorizationService,
    private val clientAuth: ClientSecretBasic,
    private val errorManager: CompanionErrorManager,
    @CompanionAppScope private val externalScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): UserRepository {

    // caches user information
    private val _user = MutableStateFlow<UserModel?>(null)
    private val _userMutex = Mutex()

    override val user: StateFlow<UserModel?>
        get(){
            runBlocking {
                _userMutex.withLock {
                    _user.value = userRemoteDataSource.user
                }
            }
            return _user.asStateFlow()
        }

    init {
        Log.i(
            TAG,
            "************** UserRepositoryImpl instance Initialisation **************"
        )
    }

    override suspend fun fetchUserInformation(refresh: Boolean) {

        withContext(ioDispatcher){
            if (refresh || _user.value == null){

                var authState: AuthState = AuthState()

                when(val result = authDataStore.fetchAuthState()){
                    is Result.Success -> authState = result.value
                    Result.Error -> Unit
                }

                authState.performActionWithFreshTokens(
                    authorizationService,
                    clientAuth,
                ) { accessToken: String?, idToken: String?, ex: AuthorizationException? ->
                    if (ex != null) {
                        runBlocking{
                            CompanionLogger.logException(
                                e = ex,
                                errorManager = errorManager,
                                msg = "Authorization failure"
                            )
                        }
                        return@performActionWithFreshTokens
                    } else {
                        val saveAuthStateJob = CoroutineScope(ioDispatcher).launch {
                            authDataStore.saveAuthState(authState)
                        }

                        val fetchUserJob = CoroutineScope(ioDispatcher).launch {
                            if (!accessToken.isNullOrEmpty()){
                                val result: Result<UserModel> = userRemoteDataSource.fetchUserInformation(accessToken = accessToken)

                                when(result){
                                    is Result.Success -> {
                                        _userMutex.withLock {
                                            _user.value = result.value
                                        }
                                        Log.i(
                                            TAG,
                                            "SUCCESS:\n-----------------user: ${_user.value?.login.toString()} \n" +
                                                    "-----------------email: ${_user.value?.email.toString()} \n" +
                                                    "-----------------mobile: ${_user.value?.mobile.toString()} \n" +
                                                    "-----------------wallet: ${_user.value?.wallet.toString()} \n" +
                                                    "-----------------location: ${_user.value?.location.toString()} \n" +
                                                    "-----------------level: ${_user.value?.level.toString()} "
                                        )
                                    }
                                    is Result.Error -> Unit
                                }
                            }
                        }

                        runBlocking {
                            saveAuthStateJob.join()
                            fetchUserJob.join()
                        }

                    }
                }
            }
        }
    }

}