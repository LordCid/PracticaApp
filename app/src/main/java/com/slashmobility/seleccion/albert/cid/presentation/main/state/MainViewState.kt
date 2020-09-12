package com.slashmobility.seleccion.albert.cid.presentation.main.state

import com.slashmobility.seleccion.albert.cid.domain.model.Group

sealed class MainViewState {
    object Loading: MainViewState()
    class ShowFullData(val groups: List<Group>): MainViewState()
    object Error: MainViewState()
}