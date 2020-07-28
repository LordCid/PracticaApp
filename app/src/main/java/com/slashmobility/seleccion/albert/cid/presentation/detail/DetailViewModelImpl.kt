package com.slashmobility.seleccion.albert.cid.presentation.detail

import androidx.lifecycle.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupUseCase
import com.slashmobility.seleccion.albert.cid.domain.usecase.SaveGroupUseCase
import com.slashmobility.seleccion.albert.cid.presentation.detail.state.DetailViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModelImpl(
    private val getGroupUseCase: GetGroupUseCase,
    private val saveGroupUseCase: SaveGroupUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : DetailViewModel, ViewModel() {

    private val _detailState: MutableLiveData<DetailViewState> = MutableLiveData()
    override val detailState: LiveData<DetailViewState>
        get() = _detailState

    override fun getGroupDetailData(id: Int) {
        viewModelScope.launch {
            val results = withContext(ioDispatcher) { getGroupUseCase(id) }
            results.fold(
                onSuccess = { _detailState.value = DetailViewState.ShowGroupData(it) },
                onFailure = { _detailState.value = DetailViewState.NoData }
            )
        }
    }

    override fun changeFavorite() {
        viewModelScope.launch{
            val state = detailState.value as DetailViewState.ShowGroupData
            val newGroup = state.group.copy(isFavorite = !state.group.isFavorite)
            val result = withContext(ioDispatcher){ saveGroupUseCase(newGroup) }
            result.onSuccess { _detailState.value = DetailViewState.ShowGroupData(newGroup)  }
        }
    }
}

class DetailViewModelFactory(
    private val getGroupUseCase: GetGroupUseCase,
    private val saveGroupUseCase: SaveGroupUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModelImpl(getGroupUseCase, saveGroupUseCase, ioDispatcher) as T
    }
}