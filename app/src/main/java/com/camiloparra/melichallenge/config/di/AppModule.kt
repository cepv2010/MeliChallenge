package com.camiloparra.melichallenge.config.di

import android.content.Context
import androidx.room.Room
import com.camiloparra.melichallenge.data.comm.BaseDataComm
import com.camiloparra.melichallenge.data.comm.ResponseHandler
import com.camiloparra.melichallenge.data.repository.DbHelper
import com.camiloparra.melichallenge.data.repository.SuggestionRepository
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCase
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCaseImpl
import com.camiloparra.melichallenge.domain.useCase.SuggestionUseCase
import com.camiloparra.melichallenge.domain.useCase.SuggestionUseCaseImpl
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
    fun providerBaseDataComm(@ApplicationContext context: Context): BaseDataComm = BaseDataComm(context)

    @Provides
    fun providerResponseHandler(@ApplicationContext context: Context): ResponseHandler = ResponseHandler(context)

    @Provides
    fun providerSuggestionUseCase(
        suggestionRepository: SuggestionRepository
    ): SuggestionUseCase {
        return SuggestionUseCaseImpl(suggestionRepository)
    }

    @Provides
    @Singleton
    fun providerDbHelper(@ApplicationContext context: Context): DbHelper = Room.databaseBuilder(
        context,
        DbHelper::class.java,
        "meli_challenge.db"
    ).build()

    @Provides
    @Singleton
    fun providerSuggestionRepository(bdHelper: DbHelper): SuggestionRepository = bdHelper.suggestionDao()


}