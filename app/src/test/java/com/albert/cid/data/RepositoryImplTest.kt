package com.albert.cid.data

import com.nhaarman.mockitokotlin2.*
import com.albert.cid.concreteGroup
import com.albert.cid.concreteOtherGroup
import com.albert.cid.data.local.LocalDataSource
import com.albert.cid.data.network.NetworkDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt


class RepositoryImplTest {

    private lateinit var sut: Repository
    private val networkDataSource = mock<NetworkDataSource>()
    private val localDataSource = mock<LocalDataSource>()

    @Before
    fun setUp() {
        sut = RepositoryImpl(networkDataSource, localDataSource)
    }

    @Test
    fun `Given network success, when get groupList, results are localy stored and returned`() {
        runBlocking {
            val expected = listOf(concreteGroup, concreteGroup)
            given(networkDataSource.getGroupList()).willReturn(Result.success(expected))

            val actual = sut.getGroupList()

            val inOrder = inOrder(networkDataSource, localDataSource)
            inOrder.verify(networkDataSource).getGroupList()
            inOrder.verify(localDataSource).storeGroupList(expected)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given network fail and data stored localy, when get groupList results are get from local`() {
        runBlocking {
            val expected = listOf(concreteGroup, concreteGroup)
            given(networkDataSource.getGroupList()).willReturn(Result.failure(mock()))
            given(localDataSource.getGroupList(any())).willReturn(Result.success(expected))

            val actual = sut.getGroupList()

            val inOrder = inOrder(networkDataSource, localDataSource)
            inOrder.verify(networkDataSource).getGroupList()
            inOrder.verify(localDataSource).getGroupList(false)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given stored favorite groups, when get favorites, then get from local`() {
        runBlocking {
            val expected = listOf(concreteOtherGroup, concreteOtherGroup)
            given(localDataSource.getGroupList(any())).willReturn(Result.success(expected))

            val actual = sut.getFavoritesGroupList()

            verify(localDataSource).getGroupList(true)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given stored groups, when get detail, then get from local`() {
        runBlocking {
            val someGroupId = 123
            val expected = concreteGroup
            given(localDataSource.getGroup(anyInt())).willReturn(Result.success(expected))

            val actual = sut.getGroupDetail(someGroupId)

            verify(localDataSource).getGroup(someGroupId)
            assertEquals(Result.success(expected), actual)
        }
    }


    @Test
    fun `Given images network result success, when get group images, then get from network`() {
        runBlocking {
            val someGroupId = 456
            val expected = listOf("url", "url")
            given(networkDataSource.getImagesList(anyInt())).willReturn(Result.success(expected))

            val actual = sut.getGroupImages(someGroupId)

            verify(networkDataSource).getImagesList(someGroupId)
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `When change group favorite status, then change is applied en local data source`() {
        runBlocking {
            val someGroupId = 456
            given(localDataSource.changeFavoriteStatus(anyInt())).willReturn(Result.success(Unit))

            val actual = sut.changeGroupFavoriteStatus(someGroupId)

            verify(localDataSource).changeFavoriteStatus(someGroupId)
        }
    }


}