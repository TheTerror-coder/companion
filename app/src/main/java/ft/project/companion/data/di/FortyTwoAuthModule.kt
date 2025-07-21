package ft.project.companion.data.di

import android.content.Context
import androidx.core.net.toUri
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FortyTwoAuthModule {

    @Provides
    @Singleton
    fun provideAuthorizationService(@ApplicationContext context: Context): AuthorizationService{
        return AuthorizationService(context)
    }

    @Provides
    @Singleton
    fun provideClientAuth(): ClientSecretBasic{
        return ClientSecretBasic(UltimApiAuthConfig.CLIENT_SECRET)
    }

    @Provides
    @Singleton
    fun provideAuthorizationServiceConfig(): AuthorizationServiceConfiguration{
        return AuthorizationServiceConfiguration(
            UltimApiAuthConfig.AUTH_URI.toUri(),
            UltimApiAuthConfig.TOKEN_URI.toUri()
        )
    }

}
