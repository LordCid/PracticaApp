package com.slashmobility.seleccion.albert.cid.data.network

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.slashmobility.seleccion.albert.cid.concreteGroup
import com.slashmobility.seleccion.albert.cid.concreteGroupNetworkModel
import com.slashmobility.seleccion.albert.cid.concreteOtherGroup
import com.slashmobility.seleccion.albert.cid.concreteOtherGroupNetworkModel
import com.slashmobility.seleccion.albert.cid.data.network.model.NetworkDataSourceMapperImpl
import com.slashmobility.seleccion.albert.cid.data.network.model.GroupNetworkModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import retrofit2.mock.Calls

class NetWorkDataSourceImplTest {

    private lateinit var sut: NetWorkDataSource
    private val apiService = mock<ApiService>()
    private val mapper = NetworkDataSourceMapperImpl()

    @Before
    fun setUp() {
        sut = NetWorkDataSourceImpl(
                apiService,
                mapper
            )
    }

    @Test
    fun `When get group list, should invoke proper apiService function`() {
        runBlocking {
            givenNetworkGetGroupListResponseOK(emptyList())

            sut.getGroupList()

            verify(apiService).getGroups()
        }
    }

    @Test
    fun `When get images, proper api function is invoked with argument`() {
        runBlocking {
            val someId = 123

            sut.getImagesList(someId)

            verify(apiService).getImages(someId)
        }
    }

    @Test
    fun `Given Success response, when get group list, then domain result success list model is returned`() {
        runBlocking {
            val expected = listOf(concreteGroup, concreteGroup)
            givenNetworkGetGroupListResponseOK(
                listOf(concreteGroupNetworkModel, concreteGroupNetworkModel)
            )

            val actual = sut.getGroupList()

            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Success response, when get OTHER group list, then domain result success list model is returned`() {
        runBlocking {
            val expected = listOf(concreteOtherGroup, concreteOtherGroup)
            givenNetworkGetGroupListResponseOK(
                listOf(concreteOtherGroupNetworkModel, concreteOtherGroupNetworkModel)
            )

            val actual = sut.getGroupList()

            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Failure response, when get group list, then return Result failure`() {
        runBlocking {
            givenNetworkGetGroupListResponseKO()

            val result = sut.getGroupList()

            assert(result.isFailure)
        }
    }

    @Test
    fun `Given Success response, when get images list by someId, result success list model is returned`() {
        runBlocking {
            val someId = 123
            val expected = listOf("imageUrl", "imageUrl")
            givenImagesListResponseOK(expected)

            val actual = sut.getImagesList(someId)

            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Success response, when get images list by someOtherId, OTHER result success list model is returned`() {
        runBlocking {
            val someOtherId = 435
            val expected = listOf("imageUrl", "imageUrl", "imageUrl", "imageUrl")
            givenImagesListResponseOK(expected)

            val actual = sut.getImagesList(someOtherId)

            assertEquals(Result.success(expected), actual)
        }
    }


    @Test
    fun `Given Failure response, when get images list, then return Result failure`() {
        runBlocking {
            val someId = 789
            givenGetImagesResponseKO()

            val result = sut.getImagesList(someId)

            assert(result.isFailure)
        }
    }


    private fun givenNetworkGetGroupListResponseOK(responseData: List<GroupNetworkModel>) {
        given(apiService.getGroups()).willReturn(Calls.response(responseData))
    }


    private fun givenNetworkGetGroupListResponseKO() {
        given(apiService.getGroups()).willReturn(Calls.failure(mock()))
    }

    private fun givenImagesListResponseOK(resultData: List<String>) {
        given(apiService.getImages(anyInt())).willReturn(Calls.response(resultData))
    }

    private fun givenGetImagesResponseKO() {
        given(apiService.getImages(anyInt())).willReturn(Calls.failure(mock()))
    }

}