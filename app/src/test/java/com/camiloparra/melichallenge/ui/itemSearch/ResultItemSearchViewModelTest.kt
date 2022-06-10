package com.camiloparra.melichallenge.ui.itemSearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.camiloparra.melichallenge.domain.dto.api.ItemResponse
import com.camiloparra.melichallenge.domain.useCase.ItemSearchUseCase
import com.camiloparra.melichallenge.TestObjects
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Camilo Parra on 9/06/2022.
 */
@ExperimentalCoroutinesApi
class ResultItemSearchViewModelTest {

    @RelaxedMockK
    lateinit var itemSearchUseCase: ItemSearchUseCase

    private lateinit var resultItemSearchViewModel: ResultItemSearchViewModel
    private val dispatcher = Dispatchers.Unconfined

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        resultItemSearchViewModel = ResultItemSearchViewModel(dispatcher, itemSearchUseCase)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the use case return the item response`() = runTest() {
        //Given
        val response = Pair(TestObjects.getListItemResponse(), true)
        coEvery {
            itemSearchUseCase.getSearchResult(TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)
        } returns response

        //When
        resultItemSearchViewModel.getSearch(TestObjects.EXAMPLE_QUERY)

        //Then
        assert(resultItemSearchViewModel.itemResult.value == response.first.itemResult)

    }

    @Test
    fun `when the use case return an empty itemResponse and false response`() = runTest() {
        //Given
        val response = Pair(ItemResponse(), false)
        coEvery {
            itemSearchUseCase.getSearchResult(TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)
        } returns response

        //When
        resultItemSearchViewModel.getSearch(TestObjects.EXAMPLE_QUERY)

        //Then
        assert(resultItemSearchViewModel.notConn.value == true)

    }
}