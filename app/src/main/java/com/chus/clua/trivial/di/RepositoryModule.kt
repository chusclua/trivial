package com.chus.clua.trivial.di

import com.chus.clua.trivial.data.local.SharedPreferencesHelper
import com.chus.clua.trivial.data.mappers.RemoteQuestionToQuestionMapper
import com.chus.clua.trivial.data.network.TriviaApiClient
import com.chus.clua.trivial.data.repositories.QuestionsRepositoryImp
import com.chus.clua.trivial.data.repositories.GameRepositoryImp
import com.chus.clua.trivial.domain.repositories.QuestionsRepository
import com.chus.clua.trivial.domain.repositories.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(
        apiClient: TriviaApiClient,
        mapper: RemoteQuestionToQuestionMapper
    ): QuestionsRepository = QuestionsRepositoryImp(apiClient, mapper)

    @Provides
    @Singleton
    fun provideScoreRepository(
        sharedPreferencesHelper: SharedPreferencesHelper
    ): GameRepository = GameRepositoryImp(sharedPreferencesHelper = sharedPreferencesHelper)

}