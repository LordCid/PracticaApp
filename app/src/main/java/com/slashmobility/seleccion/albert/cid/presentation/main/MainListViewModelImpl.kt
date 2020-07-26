package com.slashmobility.seleccion.albert.cid.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState

class MainListViewModelImpl : MainListViewModel, ViewModel() {

    override val mainViewState: LiveData<MainViewState>
        get() = TODO("Not yet implemented")

    override fun getGroups() {
        TODO("Not yet implemented")
    }

    override fun getGroupDetail() {
        TODO("Not yet implemented")
    }
}