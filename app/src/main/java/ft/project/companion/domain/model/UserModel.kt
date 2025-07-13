package ft.project.companion.domain.model

data class UserModel(
    val login: String,
    val profilePictureLink: String,
    val email: String,
    val mobile: String,
    val level: Double,
    val location: String,
    val wallet: Double,
//    val evaluations: String,
)