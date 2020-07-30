package com.slashmobility.seleccion.albert.cid.domain.mappers

import com.slashmobility.seleccion.albert.cid.data.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.concreteGroup
import com.slashmobility.seleccion.albert.cid.concreteGroupNetworkModel
import com.slashmobility.seleccion.albert.cid.concreteOtherGroup
import com.slashmobility.seleccion.albert.cid.concreteOtherGroupNetworkModel
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DomainMapperTest {

    private lateinit var sut: Mapper<GroupNetworkModel, Group>

    @Before
    fun setUp() {
        sut = DomainMapper()
    }

    @Test
    fun `Should map some network list model to list domain model`() {
        val expected = expectedModel()

        val actual = sut.mapList(givenNetworkModel())

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map OTHER network list model to list domain model`() {
        val expected = expectedOtherModel()

        val actual = sut.mapList(givenOtherNetworkModel())

        assertEquals(expected, actual)
    }

    @Test
    fun `Should map network list null values to empty string or 0 values`() {
        val expected = expectedEmptyModel()

        val actual = sut.mapList(givenNullValuesNetworkModel())

        assertEquals(expected, actual)
    }

    private fun givenNetworkModel() = listOf(concreteGroupNetworkModel)

    private fun givenOtherNetworkModel() = listOf(concreteOtherGroupNetworkModel)

    private fun givenNullValuesNetworkModel(): List<GroupNetworkModel> {
        return listOf(
            GroupNetworkModel(
                id = null,
                name = null,
                description = null,
                descriptionShort = null,
                defaultImageUrl = null,
                date = null
            )
        )
    }

    private fun expectedModel() = listOf(concreteGroup)

    private fun expectedOtherModel() = listOf(concreteOtherGroup)

    private fun expectedEmptyModel(): List<Group> {
        return listOf(
            Group(
                id = 0,
                name = "",
                description = "",
                descriptionShort = "",
                defaultImageUrl = "",
                dateLong = 0,
                isFavorite = false
            )
        )
    }
}