package ft.project.companion.data.datasource

import android.util.Log
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionLogger
import ft.project.companion.data.di.CompanionAppScope
import ft.project.companion.data.di.IoDispatcher
import ft.project.companion.data.remote.api.UserApiService
import ft.project.companion.data.remote.mapper.toDomain
import ft.project.companion.domain.model.UserModel
import ft.project.companion.domain.result.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApiService: UserApiService,
    private val errorManager: CompanionErrorManager,
    @CompanionAppScope private val externalScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    private var _user: UserModel? = null
    private val _userMutex: Mutex = Mutex()

    val user: UserModel?
        get() = _user

    init {
        Log.i(
            TAG,
            "************** UserRemoteDataSource instance Initialisation **************"
        )
    }

    suspend fun fetchUserInformation(accessToken: String): Result<UserModel> {
        val deferred = externalScope.async(ioDispatcher) {
            try {
                userApiService.fetchUserInformation(
                    authorizationHeader = "Bearer $accessToken"
                )
                    .let { response ->
                        if (response.isSuccessful) {
                            _userMutex.withLock {
                                _user = response.body()?.toDomain()
                            }
                            return@async if (_user != null) {
                                Result.Success(_user ?: UserModel())
                            } else {
                                Result.Error
                            }
                        } else {
                            when (response.code()) {
                                400 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "400: unauthorized from 42 API"
                                    )
                                }

                                401 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "401: unauthorized from 42 API"
                                    )
                                }

                                403 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "403: forbidden from 42 API"
                                    )
                                }

                                404 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "404: page or resource is not found"
                                    )
                                }

                                422 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "422: Unprocessable entity"
                                    )
                                }

                                500 -> {
                                    CompanionLogger.logError(
                                        errorManager = errorManager,
                                        msg = "500: problem with remote server; try again later"
                                    )
                                }
                            }
                            return@async Result.Error
                        }
                    }
            } catch (e: CancellationException){
                CompanionLogger.d(
                    msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
                )
                throw e
            } catch (e: Exception) {
                CompanionLogger.logException(
                    e = e,
                    errorManager = errorManager,
                    msg = "Something went wrong while fetching user information"
                )
            }
            return@async Result.Error
        }

        return deferred.await()
    }

}