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

class ChangeGroupFavoriteStatusUseCaseTest {
    private lateinit var sut: ChangeGroupFavoriteStatusUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = ChangeGroupFavoriteStatusUseCaseImpl(repository)
    }

    @Test
    fun `Should invoke change favorite status in repo and return its result`() {
        runBlocking {
            val someId = 123
            val expected = Result.success(Unit)
            given(repository.changeGroupFavoriteStatus(anyInt())).willReturn(expected)

            val actual = sut.invoke(someId)

            verify(repository).changeGroupFavoriteStatus(someId)
            assertEquals(expected, actual)
        }
    }
}