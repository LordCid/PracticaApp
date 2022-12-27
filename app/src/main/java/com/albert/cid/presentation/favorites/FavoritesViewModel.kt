package com.albert.cid.presentation.favorites

import androidx.lifecycle.LiveData
import com.albert.cid.presentation.favorites.state.FavoritesViewState

interface FavoritesViewModel {
    val favViewState: LiveData<FavoritesViewState>
    fun getFavoriteGroups()
}