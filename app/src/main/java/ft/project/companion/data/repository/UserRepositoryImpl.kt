package ft.project.companion.data.repository

import ft.project.companion.data.datasource.datastore.UserRemoteDataSource
import ft.project.companion.domain.model.UserModel
import ft.project.companion.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
): UserRepository {

    override suspend fun refreshUserInformation(): UserModel {
        return userRemoteDataSource.fetchUserInformation()
    }

}