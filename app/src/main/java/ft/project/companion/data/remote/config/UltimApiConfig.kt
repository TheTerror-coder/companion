package ft.project.companion.data.remote.config

import ft.project.companion.BuildConfig
import net.openid.appauth.ResponseTypeValues

object UltimApiAuthConfig {
    const val AUTH_URI = "https://api.intra.42.fr/oauth/authorize"
    const val TOKEN_URI = "https://api.intra.42.fr/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "public"
    const val CLIENT_ID: String = BuildConfig.CLIENT_ID
    const val CLIENT_SECRET: String = BuildConfig.CLIENT_SECRET
    const val CALLBACK_URL = "com.companion.jfaye://ultimapi.com/callback"
}
