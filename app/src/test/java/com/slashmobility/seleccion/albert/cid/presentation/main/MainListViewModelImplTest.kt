package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import com.slashmobility.seleccion.albert.cid.someGroup
import com.slashmobility.seleccion.albert.cid.someOtherGroup
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

class MainListViewModelTest {

    private lateinit var sut: MainListViewModel

    private val observer = mock<Observer<MainViewState>>()
    private val getGroupsUseCase = mock<GetGroupListUseCase>()
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val captorScreenState = argumentCaptor<MainViewState>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = MainListViewModelImpl(getGroupsUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `Should show Loading screen and call use case asyncrously`() {
        runBlocking {
            val expected = MainViewState.Loading

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer).onChanged(expected)

        }
    }

    @Test
    fun `Given data list get, it is shown into UI`() {
        runBlocking {
            val expectedList = listOf(someGroup, someGroup)
            givenSuccessResultWithValues(expectedList)

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as MainViewState.ShowFullData
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given OTHER data list get, it is shown into UI`() {
        runBlocking {
            val expectedList =  listOf(someOtherGroup, someOtherGroup)
            givenSuccessResultWithValues(expectedList)

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as MainViewState.ShowFullData
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given failure when getting product list, error is shown in the UI`() {
        runBlocking {
            givenFailureResult()

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            assert(captorScreenState.secondValue is MainViewState.Error)
        }
    }

    private suspend fun givenSuccessResultWithValues(list: List<Group>) {
        given(getGroupsUseCase.invoke()).willReturn(Result.success(list))
    }

    private suspend fun givenFailureResult() {
        given(getGroupsUseCase.invoke()).willReturn(Result.failure(mock<Exception>()))
    }

}