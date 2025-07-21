package ft.project.companion.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.data.datasource.UserRemoteDataSource
import ft.project.companion.data.remote.api.UserApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun bindUserRemoteDataSource(
        userApiService: UserApiService,
        errorManager: CompanionErrorManager,
        @CompanionAppScope externalScope: CoroutineScope,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): UserRemoteDataSource{
        return UserRemoteDataSource(
            userApiService = userApiService,
            errorManager = errorManager,
            externalScope = externalScope,
            ioDispatcher = ioDispatcher
        )
    }
}