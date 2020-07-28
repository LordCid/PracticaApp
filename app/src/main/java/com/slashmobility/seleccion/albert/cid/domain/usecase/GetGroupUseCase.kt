package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface GetGroupUseCase {
    suspend operator fun invoke(id: Int): Result<Group>
}