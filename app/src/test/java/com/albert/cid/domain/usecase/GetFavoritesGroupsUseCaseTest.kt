package com.albert.cid.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.albert.cid.concreteGroup
import com.albert.cid.data.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetFavoritesGroupsUseCaseTest {
    private lateinit var sut: GetFavoritesGroupsUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = GetFavoritesGroupsUseCaseImpl(repository)
    }

    @Test
    fun `Should invoke get favorite list in repo and return its result`() {
        runBlocking {
            val expected = Result.success(listOf(concreteGroup, concreteGroup))
            given(repository.getFavoritesGroupList()).willReturn(expected)

            val actual = sut.invoke()

            verify(repository).getFavoritesGroupList()
            assertEquals(expected, actual)
        }
    }
}