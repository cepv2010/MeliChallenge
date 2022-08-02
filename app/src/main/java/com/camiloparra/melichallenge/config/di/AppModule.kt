package com.camiloparra.melichallenge.config.di

import android.content.Context
import androidx.room.Room
import com.camiloparra.melichallenge.data.network.AppNetClient
import com.camiloparra.melichallenge.data.network.ResponseHandler
import com.camiloparra.melichallenge.data.local.AppDatabase
import com.camiloparra.melichallenge.data.local.SuggestionDao
import com.camiloparra.melichallenge.data.repository.SearchRepositoryImpl
import com.camiloparra.melichallenge.domain.repository.SearchRepository
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCase
import com.camiloparra.melichallenge.domain.useCase.SuggestionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * There are general providers according to Di
 *
 * Created by Camilo Parra on 7/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providerAppNetClient(@ApplicationContext context: Context): AppNetClient =
        AppNetClient(context)

    @Provides
    fun providerResponseHandler(@ApplicationContext context: Context): ResponseHandler =
        ResponseHandler(context)

    @Provides
    @Singleton
    fun providerDbHelper(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "meli_challenge.db"
    ).build()

    @Provides
    @Singleton
    fun providerSuggestionDao(bdHelper: AppDatabase): SuggestionDao = bdHelper.suggestionDao()

    @Provides
    fun providerSerachRepository(
        appNetClient: AppNetClient,
        responseHandler: ResponseHandler,
        suggestionDao: SuggestionDao
    ): SearchRepository = SearchRepositoryImpl(appNetClient, responseHandler, suggestionDao)

    @Provides
    fun providerSuggestionUseCase(
        searchRepository: SearchRepository
    ): SuggestionUseCase {
        return SuggestionUseCase(searchRepository)
    }

    @Provides
    fun providerItemSearchUseCase(
        searchRepository: SearchRepository
    ): ItemSearchUseCase {
        return ItemSearchUseCase(searchRepository)
    }


}