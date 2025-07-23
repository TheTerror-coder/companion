package ft.project.companion.core

import kotlinx.coroutines.CancellationException
import net.openid.appauth.AuthState

fun AuthState.copy(): AuthState?{
    try {
        return this.jsonSerializeString()
            .let { stateJsonStr ->
                AuthState.jsonDeserialize(stateJsonStr)
            }
    } catch (e: CancellationException){
        CompanionLogger.d(
            msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
        )
        throw e
    } catch (e: Exception){
        CompanionLogger.e(
            msg = "Exception: AuthState copying failure!!!"
        )
    }
    return null
}