package com.chus.clua.trivial.di

import com.chus.clua.trivial.data.network.TriviaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideTriviaApi(retrofit: Retrofit): TriviaApi = retrofit.create(TriviaApi::class.java)

}