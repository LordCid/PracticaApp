package com.albert.cid.presentation.detail

import androidx.lifecycle.LiveData
import com.albert.cid.presentation.detail.state.DetailViewState

interface DetailViewModel {
    val detailState: LiveData<DetailViewState>
    fun getGroupDetailData(id: Int)
    fun changeFavorite()
}