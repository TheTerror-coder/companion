package ft.project.companion.data.remote.api

import ft.project.companion.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @GET("me/")
    suspend fun fetchUserInformation(@Header ("Authorization") authorizationHeader: String): Response<UserDto>
}