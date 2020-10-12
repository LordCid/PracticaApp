package com.albert.cid.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.albert.cid.data.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class GetGroupImagesUseCaseTest {

    private lateinit var sut: GetGroupImagesUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = GetGroupImagesUseCaseImpl(repository)
    }

    @Test
    fun `Should invoke get group images in repo and return its result`() {
        runBlocking {
            val someId = 1234
            val expected = Result.success(listOf("url", "url"))
            given(repository.getGroupImages(anyInt())).willReturn(expected)

            val actual = sut.invoke(someId)

            verify(repository).getGroupImages(someId)
            assertEquals(expected, actual)
        }
    }
}