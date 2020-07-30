package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.network.NetWorkDataSource
import com.slashmobility.seleccion.albert.cid.domain.model.Group

import javax.inject.Inject

class GetGroupListUseCaseImpl @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource
) : GetGroupListUseCase {

    override suspend fun invoke(): Result<List<Group>> {
        return netWorkDataSource.getGroupList()
    }

}