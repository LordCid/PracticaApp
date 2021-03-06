package com.albert.cid.presentation.favorites

import androidx.lifecycle.*
import com.albert.cid.domain.usecase.GetFavoritesGroupsUseCase
import com.albert.cid.presentation.favorites.state.FavoritesViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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

class FavoritesViewModelFactory @Inject constructor(
    private val getFavoritesGroupsUseCase: GetFavoritesGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModelImpl(getFavoritesGroupsUseCase, ioDispatcher) as T
    }
}