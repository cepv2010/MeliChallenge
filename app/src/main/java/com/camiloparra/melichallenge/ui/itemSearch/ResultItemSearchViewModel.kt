package com.camiloparra.melichallenge.ui.itemSearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camiloparra.melichallenge.config.di.IoDispatcher
import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.camiloparra.melichallenge.data.network.dto.ItemResultDto
import com.camiloparra.melichallenge.domain.model.ItemResult
import com.camiloparra.melichallenge.domain.model.ProductDetail
import com.camiloparra.melichallenge.domain.useCase.GetItemSearchUseCase
import kotlinx.coroutines.*

@HiltViewModel
class ResultItemSearchViewModel @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    var getItemSearchUseCase: GetItemSearchUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)
    val itemResult = MutableLiveData<MutableList<ItemResult>>()
    val notConn = MutableLiveData<Boolean>()
    val notFound = MutableLiveData<Boolean>()
    var offset: Int = 0
    var enableSearch = true

    fun enableSearch() {
        enableSearch = true
    }

    fun disableSearch() {
        enableSearch = false
    }

    fun isEnableSearch() = enableSearch

    fun getSearch(query: String) {
        ioScope.launch {
            val resp = getItemSearchUseCase.getSearchResult(query, offset)
            withContext(Dispatchers.Main) {
                when {
                    resp.isSuccess -> {
                        postItemResult(resp.getOrNull() as ProductDetail)
                    }else -> {
                        notConn.postValue(true)
                        notFound.value = false
                    }
                }
            }
        }
        return
    }

    private fun postItemResult(resp: ProductDetail) {
        if (resp.paging.total == 0){
            notFound.postValue(true)
            notConn.value = false
            return
        }

        offset += resp.paging.limit
        var aItemResult = mutableListOf<ItemResult>()
        if (itemResult.value != null) {
            aItemResult = itemResult.value!!
        }
        aItemResult.addAll(resp.itemResult)
        itemResult.postValue(aItemResult)
        notConn.value = false
        notFound.value = false
    }


}