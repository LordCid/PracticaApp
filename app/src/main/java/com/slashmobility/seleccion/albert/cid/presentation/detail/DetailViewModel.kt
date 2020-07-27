package com.slashmobility.seleccion.albert.cid.presentation.detail

import androidx.lifecycle.LiveData
import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface DetailViewModel {
    val group: LiveData<Group>
    fun getGroup()
}