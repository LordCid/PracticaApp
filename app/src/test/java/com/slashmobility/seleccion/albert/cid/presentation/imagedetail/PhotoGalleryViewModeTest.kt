package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupImagesUseCase
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
import org.mockito.ArgumentMatchers.anyInt

class PhotoGalleryViewModeTest {
    private lateinit var sut: PhotoGalleryViewModel
    private val observer = mock<Observer<PhotoGalleryState>>()
    private val getGroupImagesUseCase = mock<GetGroupImagesUseCase>()
    private val captorScreenState = argumentCaptor<PhotoGalleryState>()

    private val someId = 123

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = PhotoGalleryViewModelImpl(getGroupImagesUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `When get images, should invoke use case`() {
        runBlocking {

            sut.viewState.observeForever(observer)
            sut.getImages(someId)

            verify(getGroupImagesUseCase).invoke(someId)
        }
    }

    @Test
    fun `Given group list getted, it is shown into UI`() {
        runBlocking {
            val expected = listOf("url", "url")
            givenSuccessResultWithValues(expected)

            sut.viewState.observeForever(observer)
            sut.getImages(someId)

            verify(observer).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.firstValue as PhotoGalleryState.ShowImages
            assertEquals(expected, capturedState.images)
        }
    }

    @Test
    fun `Given OTHER group list getted, it is shown into UI`() {
        runBlocking {
            val expected =   listOf("otherUrl", "otherUrl")
            givenSuccessResultWithValues(expected)

            sut.viewState.observeForever(observer)
            sut.getImages(someId)

            verify(observer).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.firstValue as PhotoGalleryState.ShowImages
            assertEquals(expected, capturedState.images)
        }
    }

    @Test
    fun `Given failure when getting group list, error is shown in the UI`() {
        runBlocking {
            givenFailureResult()

            sut.viewState.observeForever(observer)
            sut.getImages(someId)

            verify(observer).onChanged(captorScreenState.capture())
            assert(captorScreenState.firstValue is PhotoGalleryState.Error)
        }
    }

    private suspend fun givenSuccessResultWithValues(list: List<String>) {
        given(getGroupImagesUseCase.invoke(anyInt())).willReturn(Result.success(list))
    }

    private suspend fun givenFailureResult() {
        given(getGroupImagesUseCase.invoke(anyInt())).willReturn(Result.failure(mock()))
    }
}