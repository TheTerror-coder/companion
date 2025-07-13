package ft.project.companion.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.datasource.datastore.FortyTwoAuthDataStore
import ft.project.companion.data.datasource.provision.FortyTwoAuthFromContextProvider
import ft.project.companion.data.repository.FortyTwoAuthRepositoryImpl
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFortyTwoAuthFromContextProvider(@ApplicationContext context: Context): FortyTwoAuthFromContextProvider{
        return(FortyTwoAuthFromContextProvider(context))
    }

    @Provides
    @Singleton
    fun provideFortyTwoAuthDataStore(@ApplicationContext context: Context): FortyTwoAuthDataStore {
        return(FortyTwoAuthDataStore(context))
    }
}
