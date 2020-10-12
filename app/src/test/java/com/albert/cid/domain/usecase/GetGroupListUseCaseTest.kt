package com.albert.cid.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.albert.cid.concreteGroup
import com.albert.cid.data.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class GetGroupListUseCaseTest {
    private lateinit var sut: GetGroupListUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = GetGroupListUseCaseImpl(repository)
    }

    @Test
    fun `Should invoke get group list in repo and return its result`() {
        runBlocking {
            val expected = Result.success(listOf(concreteGroup, concreteGroup))
            given(repository.getGroupList()).willReturn(expected)

            val actual = sut.invoke()

            verify(repository).getGroupList()
            assertEquals(expected, actual)
        }
    }
}