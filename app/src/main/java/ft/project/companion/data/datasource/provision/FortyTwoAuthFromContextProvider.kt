package ft.project.companion.data.datasource.provision

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.AuthorizationService

class FortyTwoAuthFromContextProvider (
    @ApplicationContext private val context: Context
) {
    fun provideAuthService(): AuthorizationService{
        return AuthorizationService(context)
    }
}