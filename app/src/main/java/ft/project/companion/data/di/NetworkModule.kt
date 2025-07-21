package ft.project.companion.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ft.project.companion.data.remote.api.UserApiService
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import ft.project.companion.data.remote.network.LoggingOkHttpInterceptor
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

    @Provides
    @UltimApiBaseUrlString
    fun provideBaseUrl(): String = UltimApiAuthConfig.BASE_ENDPOINT

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val request = chain.request().newBuilder()
//                    here I can add more default headers
//                    .header("Authorization", "Bearer $accessToken")
                    .header("accept", "application/json")
                    .build()
                chain.proceed(request)
            }
//             here I add a request/response logger that I defined as a class in remote/network package
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
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
//        Creates an implementation of the API endpoints defined by the UserApiService interface.
        return retrofit.create(
            UserApiService::class.java
        )
    }
}
