package com.slashmobility.seleccion.albert.cid.data

import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.concreteGroup
import com.slashmobility.seleccion.albert.cid.concreteOtherGroup
import com.slashmobility.seleccion.albert.cid.data.local.LocalDataSource
import com.slashmobility.seleccion.albert.cid.data.network.NetWorkDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class RepositoryImplTest {

    private lateinit var sut: Repository
    private val networkDataSource = mock<NetWorkDataSource>()
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
    fun `Given soted favorite groups, when get favorites, these are getten from local`() {
        runBlocking {
            val expected = listOf(concreteOtherGroup, concreteOtherGroup)
            given(localDataSource.getGroupList(any())).willReturn(Result.success(expected))

            val actual = sut.getFavoritesGroupList()

            verify(localDataSource).getGroupList(true)
            assertEquals(Result.success(expected), actual)
        }
    }

}