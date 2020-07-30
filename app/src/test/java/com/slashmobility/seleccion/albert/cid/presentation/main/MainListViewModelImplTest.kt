package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
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
    fun `Should show Loading screen when invoke use case`() {
        runBlocking {
            val expected = MainViewState.Loading

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(getGroupsUseCase).invoke()
            verify(observer).onChanged(expected)

        }
    }

    @Test
    fun `Given group list getted, it is shown into UI`() {
        runBlocking {
            val expectedList = listOf(concreteGroup, concreteGroup)
            givenSuccessResultWithValues(expectedList)

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as MainViewState.ShowFullData
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given OTHER group list getted, it is shown into UI`() {
        runBlocking {
            val expectedList =  listOf(concreteOtherGroup, concreteOtherGroup)
            givenSuccessResultWithValues(expectedList)

            sut.mainViewState.observeForever(observer)
            sut.getGroups()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as MainViewState.ShowFullData
            assertEquals(expectedList, capturedState.groups)
        }
    }

    @Test
    fun `Given failure when getting group list, error is shown in the UI`() {
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