package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface SaveGroupUseCase {
    suspend operator fun invoke(group: Group): Result<Unit>
}
