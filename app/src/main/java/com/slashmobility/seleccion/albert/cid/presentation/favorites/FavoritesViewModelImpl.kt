package com.slashmobility.seleccion.albert.cid.presentation.favorites

import androidx.lifecycle.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetFavoritesGroupsUseCase
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModelImpl(
    private val getFavoritesGroupsUseCase: GetFavoritesGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : FavoritesViewModel, ViewModel() {

    private val _favViewState: MutableLiveData<FavoritesViewState> = MutableLiveData()
    override val favViewState: LiveData<FavoritesViewState>
        get() = _favViewState

    override fun getFavoriteGroups() {
        viewModelScope.launch {
            val results = withContext(ioDispatcher) { getFavoritesGroupsUseCase() }
            results.fold(
                onSuccess = {
                    if(it.isNotEmpty()) _favViewState.value = FavoritesViewState.ShowGroups(it)
                    else _favViewState.value = FavoritesViewState.None
                },
                onFailure = {
                    _favViewState.value = FavoritesViewState.None

                }
            )
        }
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