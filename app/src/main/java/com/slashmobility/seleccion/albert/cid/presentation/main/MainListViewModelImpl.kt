package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.lifecycle.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupsUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import kotlinx.coroutines.*

class MainListViewModelImpl(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : MainListViewModel, ViewModel() {

    private val _mainViewState: MutableLiveData<MainViewState> = MutableLiveData()
    override val mainViewState: LiveData<MainViewState>
        get() = _mainViewState

    override fun getGroups() {
        viewModelScope.launch {
            _mainViewState.value = MainViewState.Loading
            val results = withContext(ioDispatcher) { getGroupsUseCase() }
            results.fold(
                onSuccess = {
//                    dataList = it
                    _mainViewState.value = MainViewState.ShowFullData(it)
                },
                onFailure = {
                    _mainViewState.value = MainViewState.Error

                }
            )
        }
    }

    override fun getGroupDetail() {
    }
}

class MainListViewModelFactory(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainListViewModelImpl(getGroupsUseCase, ioDispatcher) as T
    }
}