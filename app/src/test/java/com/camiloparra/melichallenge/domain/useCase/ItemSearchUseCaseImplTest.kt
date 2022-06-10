package com.camiloparra.melichallenge.domain.useCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.camiloparra.melichallenge.TestObjects
import com.camiloparra.melichallenge.data.comm.Api.ItemSearchApi
import com.camiloparra.melichallenge.data.comm.BaseDataComm
import com.camiloparra.melichallenge.data.comm.ResponseHandler
import com.camiloparra.melichallenge.domain.dto.api.ItemResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

/**
 * Created by Camilo Parra on 9/06/2022.
 */
class ItemSearchUseCaseImplTest {

    //    private var baseDataComm = mockk<BaseDataComm>()
//    private var responseHandler = mockk<ResponseHandler>()
    @RelaxedMockK
    @MockK
    private lateinit var baseDataComm: BaseDataComm

    @RelaxedMockK
    @MockK
    private lateinit var responseHandler: ResponseHandler

    private lateinit var itemSearchUseCaseImpl: ItemSearchUseCaseImpl

    @get:Rule
    var rule =  InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        itemSearchUseCaseImpl = ItemSearchUseCaseImpl(baseDataComm, responseHandler)
    }

    @Test
    fun `when the search items by query api make an exception by timeout`() {
        CoroutineScope(Dispatchers.IO).launch {
            //Given
            coEvery {
                val apiTest = baseDataComm.getApi(ItemSearchApi::class.java)
                apiTest.getSearch(TestObjects.SITE_ID, TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)
            }.throws(Exception())

            //When
            val result = itemSearchUseCaseImpl.getSearchResult(TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)

            //Then
            assert(result ==  Pair(ItemResponse(), false))
        }
    }

    @Test
    fun `when the items by query api return an ok status code`() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = TestObjects.getListItemResponse()
            //Given
            coEvery {
                val apiTest = baseDataComm.getApi(ItemSearchApi::class.java)
                apiTest.getSearch(TestObjects.SITE_ID, TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)
            } returns response

            //When
            val result = itemSearchUseCaseImpl.getSearchResult(TestObjects.EXAMPLE_QUERY, TestObjects.EXAMPLE_OFFSET)

            //Then
            assert(result == Pair(response, true))
        }
    }
}
