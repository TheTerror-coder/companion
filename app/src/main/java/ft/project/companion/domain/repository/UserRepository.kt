package ft.project.companion.domain.repository

import ft.project.companion.domain.model.UserModel
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    suspend fun refreshUserInformation(): UserModel
}