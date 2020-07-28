package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.lifecycle.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import kotlinx.coroutines.*
import javax.inject.Inject

class MainListViewModelImpl(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : MainListViewModel, ViewModel() {

    private val _mainViewState: MutableLiveData<MainViewState> = MutableLiveData()
    override val mainViewState: LiveData<MainViewState>
        get() = _mainViewState

    override fun getGroups() {
        viewModelScope.launch {
            _mainViewState.value = MainViewState.Loading
            val results = withContext(ioDispatcher) { getGroupListUseCase() }
            results.fold(
                onSuccess = {
                    _mainViewState.value = MainViewState.ShowFullData(it)
                },
                onFailure = {
                    _mainViewState.value = MainViewState.Error

                }
            )
        }
    }
}

class MainListViewModelFactory @Inject constructor(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainListViewModelImpl(getGroupListUseCase, ioDispatcher) as T
    }
}