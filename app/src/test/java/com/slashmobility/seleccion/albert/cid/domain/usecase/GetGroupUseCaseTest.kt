package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.slashmobility.seleccion.albert.cid.concreteGroup
import com.slashmobility.seleccion.albert.cid.data.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class GetGroupUseCaseTest {
    private lateinit var sut: GetGroupUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = GetGroupUseCaseImpl(repository)
    }

    @Test
    fun `Should invoke get group detail in repo with the proper id and return its result`() {
        runBlocking {
            val someId = 123
            val expected = Result.success(concreteGroup)
            given(repository.getGroupDetail(anyInt())).willReturn(expected)

            val actual = sut.invoke(someId)

            verify(repository).getGroupDetail(someId)
            assertEquals(expected, actual)
        }
    }
}