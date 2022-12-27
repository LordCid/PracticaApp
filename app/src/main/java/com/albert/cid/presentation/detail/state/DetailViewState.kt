package com.albert.cid.presentation.detail.state

import com.albert.cid.domain.model.Group

sealed class DetailViewState {
    class ShowGroupData(val group: Group): DetailViewState()
    object NoData: DetailViewState()
}