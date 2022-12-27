package com.albert.cid.presentation.main

import androidx.lifecycle.LiveData
import com.albert.cid.presentation.main.state.MainViewState

interface MainListViewModel {
    val mainViewState: LiveData<MainViewState>
    fun getGroups()
}