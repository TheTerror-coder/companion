package ft.project.companion.data.datasource.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.AuthorizationService

class FortyTwoAuthLocalDataSource (
    @ApplicationContext private val context: Context
) {
    fun provideAuthService(): AuthorizationService{
        return AuthorizationService(context)
    }
}