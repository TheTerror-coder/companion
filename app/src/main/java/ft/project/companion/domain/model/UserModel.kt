package ft.project.companion.domain.model

private const val INITIAL_STR_VALUE: String = "unknown"
private const val INITIAL_DOUBLE_VALUE: Double = 0.0

data class UserModel(
    val login: String = INITIAL_STR_VALUE,
    val profilePictureLink: String = INITIAL_STR_VALUE,
    val email: String = INITIAL_STR_VALUE,
    val mobile: String = INITIAL_STR_VALUE,
    val level: Double = INITIAL_DOUBLE_VALUE,
    val location: String = INITIAL_STR_VALUE,
    val wallet: Double = INITIAL_DOUBLE_VALUE,
//    val evaluations: String = INITIAL_STR_VALUE,
){
//    val firstInitFromRemoteIsDone: Boolean
//        get() = false.let {
//            (login != INITIAL_STR_VALUE ||
//                    email != INITIAL_STR_VALUE ||
//                    level != INITIAL_DOUBLE_VALUE ||
//                    wallet != INITIAL_DOUBLE_VALUE)
//        }

}
