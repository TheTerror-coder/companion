package ft.project.companion.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.data.datasource.datastore.AuthDataStore
import ft.project.companion.data.datasource.datastore.AuthDataStoreImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideAuthDataStore(
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        @CompanionAppScope externalScope: CoroutineScope,
        errorManager: CompanionErrorManager
    ): AuthDataStore {
        return(AuthDataStoreImpl(
            errorManager = errorManager,
            context = context,
            ioDispatcher = ioDispatcher,
            externalScope = externalScope
        ))
    }
}