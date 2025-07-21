package ft.project.companion.core

import javax.inject.Inject

class CompanionAuthorizationRequestException(
    msg: String = "AuthorizationException thrown",
    override val cause: Throwable? = null,
): Exception(msg)

class CompanionTokenRequestException(
    msg: String = "AuthorizationException(from token request) thrown",
    override val cause: Throwable? = null,
): Exception(msg)

class SaveAuthStateFailureException(
    msg: String = "IOException thrown while writing data to disk",
    override val cause: Throwable? = null,
): Exception(msg)
