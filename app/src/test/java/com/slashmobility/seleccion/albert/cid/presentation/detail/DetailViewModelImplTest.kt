package com.slashmobility.seleccion.albert.cid.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupUseCase
import com.slashmobility.seleccion.albert.cid.domain.usecase.SaveGroupUseCase
import com.slashmobility.seleccion.albert.cid.presentation.detail.state.DetailViewState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class DetailViewModelImplTest {


    private lateinit var sut: DetailViewModel

    private val observer = mock<Observer<DetailViewState>>()
    private val getGroupUseCase = mock<GetGroupUseCase>()
    private val saveGroupUseCase= mock<SaveGroupUseCase>()
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val captorScreenState = argumentCaptor<DetailViewState>()


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = DetailViewModelImpl(getGroupUseCase, saveGroupUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `Should invoke usecase When get group data`() {
        runBlocking {
            val someId = 123
            sut.getGroupDetailData(someId)

            verify(getGroupUseCase).invoke(someId)
        }
    }

    @Test
    fun `Given some group, it is shown in UI`() {
        runBlocking {
            val someId = 123
            val expected = getSomeGroupById(someId)
            givenSuccessGetGroup(expected)

            sut.detailState.observeForever(observer)
            sut.getGroupDetailData(someId)

            verify(observer).onChanged(captorScreenState.capture())
            val actual = (captorScreenState.firstValue as DetailViewState.ShowGroupData).group
            assertEquals(expected, actual)
        }
    }


    @Test
    fun `Given OTHER group, it is shown in UI`() {
        runBlocking {
            val someId = 456
            val expected = getSomeGroupById(someId)
            givenSuccessGetGroup(expected)

            sut.detailState.observeForever(observer)
            sut.getGroupDetailData(someId)

            verify(observer).onChanged(captorScreenState.capture())
            val actual = (captorScreenState.firstValue as DetailViewState.ShowGroupData).group
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Given failure, when get group data, no data is shown in UI`() {
        runBlocking {
            val anyId = 765
            givenGetGroupFailure()

            sut.detailState.observeForever(observer)
            sut.getGroupDetailData(anyId)

            verify(observer).onChanged(captorScreenState.capture())
            assert(captorScreenState.firstValue is DetailViewState.NoData)
        }
    }

    @Test
    fun `Given NON favorite group, when favorite action, group is stored favorite`() {
        runBlocking {
            val expected = getSomeGroupByFavorite(true)
            val group = getSomeGroupByFavorite(false)
            givenLoadedGroupData(group)

            sut.changeFavorite()

            verify(saveGroupUseCase).invoke(expected)
        }
    }

    @Test
    fun `Given favorite group, when favorite action, group is stored NO favorite`() {
        runBlocking {
            val expected = getSomeGroupByFavorite(false)
            val group = getSomeGroupByFavorite(true)
            givenLoadedGroupData(group)

            sut.changeFavorite()

            verify(saveGroupUseCase).invoke(expected)
        }
    }

    @Test
    fun `Given group save Success, UI is Updated`() {
        runBlocking {
            val expected = getSomeGroupByFavorite(true)
            val group = getSomeGroupByFavorite(false)
            givenLoadedGroupData(group)
            givenStoreGroupSuccess()

            sut.changeFavorite()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val actual = (captorScreenState.secondValue as DetailViewState.ShowGroupData).group
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Given group save Failure, UI not updated`() {
        runBlocking {
            val group = getSomeGroupByFavorite(false)
            givenLoadedGroupData(group)
            givenStoreGroupFailure()

            sut.changeFavorite()

            verify(observer, times(1)).onChanged(any())
        }
    }

    private suspend fun givenLoadedGroupData(group: Group) {
        givenSuccessGetGroup(group)
        sut.detailState.observeForever(observer)
        sut.getGroupDetailData(group.id)
    }

    private fun getSomeGroupByFavorite(isFavorite: Boolean) = Group(
        id = 1,
        name = "Name",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "url",
        dateLong = 1677,
        isFavorite = isFavorite
    )

    private fun getSomeGroupById(id: Int) = Group(
        id = id,
        name = "Name",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "url",
        dateLong = 1677,
        isFavorite = false
    )


    private suspend fun givenGetGroupFailure() {
        given(getGroupUseCase.invoke(any())).willReturn(Result.failure(mock()))
    }

    private suspend fun givenSuccessGetGroup(group: Group) {
        given(getGroupUseCase.invoke(group.id)).willReturn(Result.success(group))
    }

    private suspend fun givenStoreGroupSuccess(){
        given(saveGroupUseCase.invoke(any())).willReturn(Result.success(Unit))
    }

    private suspend fun givenStoreGroupFailure(){
        given(saveGroupUseCase.invoke(any())).willReturn(Result.failure(mock()))
    }
}