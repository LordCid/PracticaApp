package com.slashmobility.seleccion.albert.cid.presentation.main.state

import com.slashmobility.seleccion.albert.cid.domain.model.FanGroup

sealed class MainState {
    object Loading: MainState()
    class ShowFullData(val groups: List<FanGroup>): MainState()
    class ShowFavorites(val groups: List<FanGroup>): MainState()
    object Error: MainState()
}