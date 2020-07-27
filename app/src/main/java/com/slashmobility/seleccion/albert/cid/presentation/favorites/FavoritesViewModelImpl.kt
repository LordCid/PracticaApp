package com.slashmobility.seleccion.albert.cid.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetFavoritesGroupsUseCase
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import kotlinx.coroutines.CoroutineDispatcher

class FavoritesViewModelImpl(
    private val getFavoritesGroupsUseCase: GetFavoritesGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : FavoritesViewModel {
    override val mainViewState: LiveData<FavoritesViewState>
        get() = TODO("Not yet implemented")

    override fun getFavoriteGroups() {
        TODO("Not yet implemented")
    }
}

class FavoritesViewModelFactory(
    private val getFavoritesGroupsUseCase: GetFavoritesGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModelImpl(getFavoritesGroupsUseCase, ioDispatcher) as T
    }
}