package com.camiloparra.melichallenge.config.di

import android.content.Context
import androidx.room.Room
import com.camiloparra.melichallenge.data.network.AppNetClient
import com.camiloparra.melichallenge.data.network.ResponseHandler
import com.camiloparra.melichallenge.data.local.AppDatabase
import com.camiloparra.melichallenge.data.local.SuggestionDao
import com.camiloparra.melichallenge.data.repository.ProductRepositoryImpl
import com.camiloparra.melichallenge.data.repository.SuggestionRepositoryImpl
import com.camiloparra.melichallenge.domain.repository.ProductRepository
import com.camiloparra.melichallenge.domain.repository.SuggestionRepository
import com.camiloparra.melichallenge.domain.useCase.AddSuggestionUseCase
import com.camiloparra.melichallenge.domain.useCase.GetSuggestionUseCase
import com.camiloparra.melichallenge.domain.useCase.GetItemSearchUseCase
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
    fun providerProductRepository(
        appNetClient: AppNetClient,
        responseHandler: ResponseHandler
    ): ProductRepository = ProductRepositoryImpl(appNetClient, responseHandler)

    @Provides
    fun providerSuggestionRepository(
        suggestionDao: SuggestionDao
    ): SuggestionRepository = SuggestionRepositoryImpl(suggestionDao)

    @Provides
    fun providerAddSuggestionUseCase(
        suggestionRepository: SuggestionRepository
    ): AddSuggestionUseCase {
        return AddSuggestionUseCase(suggestionRepository)
    }

    @Provides
    fun providerGetSuggestionUseCase(
        suggestionRepository: SuggestionRepository
    ): GetSuggestionUseCase {
        return GetSuggestionUseCase(suggestionRepository)
    }

    @Provides
    fun providerGetItemSearchUseCase(
        productRepository: ProductRepository
    ): GetItemSearchUseCase {
        return GetItemSearchUseCase(productRepository)
    }


}