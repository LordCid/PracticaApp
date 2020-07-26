package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        TODO("Not yet implemented")
    }
}