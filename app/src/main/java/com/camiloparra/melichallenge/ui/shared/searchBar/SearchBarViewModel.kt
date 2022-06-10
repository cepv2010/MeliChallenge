package com.camiloparra.melichallenge.ui.shared.searchBar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camiloparra.melichallenge.config.di.IoDispatcher
import com.camiloparra.melichallenge.domain.entity.Suggestion
import com.camiloparra.melichallenge.domain.useCase.SuggestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@HiltViewModel
class SearchBarViewModel @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    var suggestionUseCase: SuggestionUseCase
) :  ViewModel() {

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)
    var suggestionResult = MutableLiveData<List<Suggestion>>()

    fun getSuggestion(){
        ioScope.launch{
            val rSuggestion = suggestionUseCase.getSuggestion()
            suggestionResult.postValue(rSuggestion)
        }
    }

    fun insertSuggestion(query: String){
        val suggestion = Suggestion()
        suggestion.suggest = query
        ioScope.launch{
            suggestionUseCase.insertSuggestion(suggestion)
        }
    }

}