package com.slashmobility.seleccion.albert.cid.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetFavoritesGroupsUseCase
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import com.slashmobility.seleccion.albert.cid.concreteGroup
import com.slashmobility.seleccion.albert.cid.concreteOtherGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class FavoritesViewModelTest {

    private lateinit var sut: FavoritesViewModel

    private val observer = mock<Observer<FavoritesViewState>>()
    private val getFavGroupsUseCase = mock<GetFavoritesGroupsUseCase>()
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val captorScreenState = argumentCaptor<FavoritesViewState>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = FavoritesViewModelImpl(getFavGroupsUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `Given favorite group list, it is shown into UI`() {
        runBlocking {
            val expectedList = listOf(concreteGroup, concreteGroup)
            givenFavorites(expectedList)

            sut.favViewState.observeForever(observer)
            sut.getFavoriteGroups()

            verify(observer).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.firstValue as FavoritesViewState.ShowGroups
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given OTHER favorite group list, it is shown into UI`() {
        runBlocking {
            val expectedList =  listOf(concreteOtherGroup, concreteOtherGroup)
            givenFavorites(expectedList)

            sut.favViewState.observeForever(observer)
            sut.getFavoriteGroups()

            verify(observer).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.firstValue as FavoritesViewState.ShowGroups
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given no favorite list, no favorites label is shown in UI`() {
        runBlocking {
            givenNoFavorites()

            sut.favViewState.observeForever(observer)
            sut.getFavoriteGroups()

            verify(observer).onChanged(FavoritesViewState.None)
        }
    }

    @Test
    fun `Given failure when getting favorite group list, no favorites label is shown in the UI`() {
        runBlocking {
            givenFailure()

            sut.favViewState.observeForever(observer)
            sut.getFavoriteGroups()

            verify(observer).onChanged(FavoritesViewState.None)
        }
    }

    private suspend fun givenFavorites(list: List<Group>) {
        given(getFavGroupsUseCase.invoke()).willReturn(Result.success(list))
    }

    private suspend fun givenFailure() {
        given(getFavGroupsUseCase.invoke()).willReturn(Result.failure(mock<Exception>()))
    }

    private suspend fun givenNoFavorites() {
        given(getFavGroupsUseCase.invoke()).willReturn(Result.success(emptyList()))
    }
}