package com.albert.cid.presentation.favorites.state

import com.albert.cid.domain.model.Group

sealed class FavoritesViewState {
    object None: FavoritesViewState()
    class ShowGroups(val groups: List<Group>): FavoritesViewState()
}