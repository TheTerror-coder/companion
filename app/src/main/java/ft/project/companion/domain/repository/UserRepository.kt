package ft.project.companion.domain.repository

import ft.project.companion.domain.model.UserModel
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val user: StateFlow<UserModel?>

    suspend fun fetchUserInformation(refresh: Boolean = false)
}