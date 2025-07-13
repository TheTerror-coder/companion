package ft.project.companion.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.repository.FortyTwoAuthRepositoryImpl
import ft.project.companion.data.repository.UserRepositoryImpl
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import ft.project.companion.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindFortyTwoAuth(
        fortyTwoAuthRepositoryImpl: FortyTwoAuthRepositoryImpl
    ): FortyTwoAuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}
