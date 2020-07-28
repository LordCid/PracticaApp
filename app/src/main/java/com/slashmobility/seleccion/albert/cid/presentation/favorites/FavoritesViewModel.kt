package com.slashmobility.seleccion.albert.cid.presentation.favorites

import androidx.lifecycle.LiveData
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState

interface FavoritesViewModel {
    val favViewState: LiveData<FavoritesViewState>
    fun getFavoriteGroups()
}