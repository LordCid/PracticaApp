package com.slashmobility.seleccion.albert.cid.presentation.detail

import androidx.lifecycle.LiveData
import com.slashmobility.seleccion.albert.cid.presentation.detail.state.DetailViewState

interface DetailViewModel {
    val detailState: LiveData<DetailViewState>
    fun getGroupDetailData()
    fun changeFavorite()
}