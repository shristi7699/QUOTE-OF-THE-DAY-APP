package com.app.motivationapp.di

import com.app.motivationapp.data.remote.MotivationApi
import com.app.motivationapp.data.remote.MotivationApi.Companion.BASE_URL
import com.app.motivationapp.data.repository.MotivationRepositoryImpl
import com.app.motivationapp.domain.repository.MotivationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesMotivationApi(): MotivationApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(MotivationApi::class.java)

    }


    @Provides
    @Singleton
    fun providesMotivationRepository(motivationApi: MotivationApi) : MotivationRepository{
        return MotivationRepositoryImpl(motivationApi)
    }


}