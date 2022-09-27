package com.iyanuoluwa.inews.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iyanuoluwa.inews.data.remote.NewsApi
import com.iyanuoluwa.inews.data.repository.NewsRepositoryImpl
import com.iyanuoluwa.inews.domain.repository.NewsRepository
import com.iyanuoluwa.inews.domain.usecase.HomeFragmentUseCase
import com.iyanuoluwa.inews.domain.usecase.OtherFragmentsUseCase
import com.iyanuoluwa.inews.domain.usecase.UseCases
import com.iyanuoluwa.inews.util.BASE_URL
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
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): NewsApi {
        return retrofit
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Singleton
    @Provides
    fun provideUseCases(newsRepository: NewsRepository): UseCases {
        return UseCases(
            HomeFragmentUseCase(newsRepository),
            OtherFragmentsUseCase(newsRepository)
        )
    }
}