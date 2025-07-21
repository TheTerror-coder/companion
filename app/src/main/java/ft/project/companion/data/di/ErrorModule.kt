package ft.project.companion.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionErrorManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ErrorModule {

    @Provides
    @Singleton
    fun provideCompanionErrorManagerImpl(): CompanionErrorManagerImpl{
        return CompanionErrorManagerImpl()
    }

    @Provides
    @Singleton
    fun provideCompanionErrorManager(impl: CompanionErrorManagerImpl): CompanionErrorManager{
        return impl
    }
}