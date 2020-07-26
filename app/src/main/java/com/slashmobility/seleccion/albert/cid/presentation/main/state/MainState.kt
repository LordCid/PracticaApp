package com.slashmobility.seleccion.albert.cid.presentation.main.state

import com.slashmobility.seleccion.albert.cid.domain.model.Group

sealed class MainState {
    object Loading: MainState()
    class ShowFullData(val groups: List<Group>): MainState()
    class ShowFavorites(val groups: List<Group>): MainState()
    object Error: MainState()
}