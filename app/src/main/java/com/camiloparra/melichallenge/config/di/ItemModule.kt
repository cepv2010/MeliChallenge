package com.camiloparra.melichallenge.config.di

import com.camiloparra.melichallenge.data.comm.BaseDataComm
import com.camiloparra.melichallenge.data.comm.ResponseHandler
import com.camiloparra.melichallenge.data.repository.SuggestionRepository
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCase
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Providers for Items domain like search items
 *
 * Created by Camilo Parra on 7/06/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class ItemModule {

    @Provides
    fun providerItemSearchUseCase(
        baseDataComm: BaseDataComm,
        responseHandler: ResponseHandler,
    ): ItemSearchUseCase {
        return ItemSearchUseCaseImpl(baseDataComm, responseHandler)
    }

}