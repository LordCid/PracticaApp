package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface GetGroupListUseCase {
    suspend operator fun invoke(): Result<List<Group>>
}