package com.slashmobility.seleccion.albert.cid.presentation.main.state

import com.slashmobility.seleccion.albert.cid.domain.model.Group

sealed class DetailViewState {
    class ShowGroupData(val group: Group): DetailViewState()
    object Error: DetailViewState()
}