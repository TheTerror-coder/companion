package ft.project.companion.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("login") val login: String?,
    @SerialName("image") val image: Image,
    @SerialName("email") val email: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("location") val location: String?,
    @SerialName("wallet") val wallet: Double,
    @SerialName("cursus_users") val cursusUsers: ArrayList<CursusUsersItem>,
//    val evaluations: String,
)

@Serializable
data class Image(
    @SerialName("link") val link: String?,
    @SerialName("versions") val versions: ImageVersion,
)

@Serializable
data class ImageVersion(
    @SerialName("large") val large: String?,
    @SerialName("medium") val medium: String?,
    @SerialName("small") val small: String?,
    @SerialName("micro") val micro: String?,
)

@Serializable
data class CursusUsersItem(
    @SerialName("id") val id: Double,
    @SerialName("begin_at") val begin_at: String?,
    @SerialName("end_at") val end_at: String?,
    @SerialName("grade") val grade: String?,
    @SerialName("level") val level: Double,
    @SerialName("cursus_id") val cursus_id: Double,
    @SerialName("has_coalition") val has_coalition: Boolean,
    @SerialName("blackholed_at") val blackholed_at: String?,
    @SerialName("created_at") val created_at: String?,
    @SerialName("updated_at") val updated_at: String?,
)