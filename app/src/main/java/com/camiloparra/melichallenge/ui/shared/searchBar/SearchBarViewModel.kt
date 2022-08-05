package com.camiloparra.melichallenge.ui.shared.searchBar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camiloparra.melichallenge.config.di.IoDispatcher
import com.camiloparra.melichallenge.domain.model.Suggestion
import com.camiloparra.melichallenge.domain.useCase.AddSuggestionUseCase
import com.camiloparra.melichallenge.domain.useCase.GetSuggestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@HiltViewModel
class SearchBarViewModel @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    var addSuggestionUseCase: AddSuggestionUseCase,
    var getSuggestionUseCase: GetSuggestionUseCase
) :  ViewModel() {

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)
    var suggestionResult = MutableLiveData<List<Suggestion>>()

    fun getSuggestion(){
        ioScope.launch{
            val rSuggestion = getSuggestionUseCase.getSuggestion()
            suggestionResult.postValue(rSuggestion)
        }
    }

    fun insertSuggestion(query: String){
        val suggestion = Suggestion(
            suggest = query
        )
        ioScope.launch{
            addSuggestionUseCase.insertSuggestion(suggestion)
        }
    }

}