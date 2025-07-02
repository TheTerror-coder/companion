package ft.project.companion.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.datasource.local.FortyTwoAuthLocalDataSource
import ft.project.companion.data.repository.FortyTwoAuthRepositoryImpl
import ft.project.companion.domain.fortytwoauth.FortyTwoAuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FortyTwoAuthModules{

    @Binds
    @Singleton
    abstract fun bindFortyTwoAuth(
        fortyTwoAuthRepositoryImpl: FortyTwoAuthRepositoryImpl
    ): FortyTwoAuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFortyTwoAuthLocalDataSource(@ApplicationContext context: Context): FortyTwoAuthLocalDataSource{
        return(FortyTwoAuthLocalDataSource(context))
    }
}
