package com.slashmobility.seleccion.albert.cid.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelImpl
import kotlinx.coroutines.CoroutineDispatcher

class DetailViewModelImpl(
    private val getGroupUseCase: GetGroupUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : DetailViewModel, ViewModel() {
    override val group: LiveData<Group>
        get() = TODO("Not yet implemented")

    override fun getGroup() {

    }
}

class DetailViewModelFactory(
    private val getGroupUseCase: GetGroupUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModelImpl(getGroupUseCase, ioDispatcher) as T
    }
}