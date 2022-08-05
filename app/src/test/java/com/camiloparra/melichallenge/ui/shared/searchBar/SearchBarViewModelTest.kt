package com.camiloparra.melichallenge.ui.shared.searchBar

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.camiloparra.melichallenge.TestObjects
import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.*

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@ExperimentalCoroutinesApi
class SearchBarViewModelTest{
    @RelaxedMockK
    lateinit var suggestionUseCase: SuggestionUseCase

    private lateinit var searchBarViewModel: SearchBarViewModel
    private val dispatcher = Dispatchers.Unconfined

    @get:Rule
    var rule =  InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchBarViewModel = SearchBarViewModel(dispatcher, suggestionUseCase)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the suggestion result not return data`() = runTest {
        //Given
        val response: List<SuggestionEntity> = listOf()
        coEvery {
            suggestionUseCase.getSuggestion()
        } returns response

        //When
        searchBarViewModel.getSuggestion()

        //Then
        assert(searchBarViewModel.suggestionResult.value == response)
    }

    @Test
    fun `when the suggestion result return data`() = runTest {
        //Given
        val response: List<SuggestionEntity> = TestObjects.getSuggestion()
        coEvery {
            suggestionUseCase.getSuggestion()
        } returns response

        //When
        searchBarViewModel.getSuggestion()

        //Then
        assert(searchBarViewModel.suggestionResult.value == response)
    }
}