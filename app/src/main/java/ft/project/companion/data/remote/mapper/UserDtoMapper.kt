package ft.project.companion.data.remote.mapper

import ft.project.companion.data.remote.dto.UserDto
import ft.project.companion.domain.model.UserModel
import kotlin.String

fun UserDto.toDomain(): UserModel{
    return UserModel(
        login = login.toString(),
        profilePictureLink = image.link.toString(),
        email = email.toString(),
        mobile = phone.toString(),
        level = cursusUsers.last().level,
        location = location.toString(),
        wallet = wallet,
//    val evaluations: String,
    )
}