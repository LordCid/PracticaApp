package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import androidx.lifecycle.*
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupImagesUseCase
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoGalleryViewModelImpl(
    private val getGroupImagesUseCase: GetGroupImagesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : PhotoGalleryViewModel, ViewModel() {

    private val _viewState: MutableLiveData<PhotoGalleryState> = MutableLiveData()
    override val viewState: LiveData<PhotoGalleryState>
        get() = _viewState

    override fun getImages() {
        viewModelScope.launch {
            val results = withContext(ioDispatcher) { getGroupImagesUseCase() }
            results.fold(
                onSuccess = {
                    _viewState.value = PhotoGalleryState.ShowImages(it)
                },
                onFailure = {
                    _viewState.value = PhotoGalleryState.Error

                }
            )
        }
    }
}
class PhotoGalleryViewModeFactory @Inject constructor(
    private val getGroupImagesUseCase: GetGroupImagesUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoGalleryViewModelImpl(getGroupImagesUseCase, ioDispatcher) as T
    }
}