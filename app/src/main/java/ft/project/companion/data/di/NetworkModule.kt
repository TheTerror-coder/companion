package ft.project.companion.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.datasource.datastore.FortyTwoAuthDataStore
import ft.project.companion.data.remote.api.UserApiService
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import ft.project.companion.data.remote.network.LoggingOkHttpInterceptor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UltimApiBaseUrlString

@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {

    @UltimApiBaseUrlString
    @Provides
    fun provideBaseUrl(): String = UltimApiAuthConfig.BASE_ENDPOINT

    @Provides
    @Singleton
    fun provideOkHttpClient(fortyTwoAuthDataStore: FortyTwoAuthDataStore): OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val accessToken: String? = runBlocking {
                    fortyTwoAuthDataStore.getAccessToken().first()
                }
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .header("accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(LoggingOkHttpInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@UltimApiBaseUrlString baseUrl: String, okHttpClient: OkHttpClient): Retrofit{


        val aKotlinSerialisationJson = Json { ignoreUnknownKeys = true }

        return (
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(aKotlinSerialisationJson.asConverterFactory("application/json; charset=UTF8".toMediaType()))
                    .build()
                )
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService = retrofit.create<UserApiService>(
        UserApiService::class.java)
}
