package ft.project.companion.data.datasource.datastore

import ft.project.companion.data.remote.api.UserApiService
import ft.project.companion.data.remote.dto.UserDto
import ft.project.companion.data.remote.mapper.toDomain
import ft.project.companion.domain.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApiService: UserApiService,
//    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun fetchUserInformation(): UserModel = userApiService.fetchUserInformation().toDomain()

}