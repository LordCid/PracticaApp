package com.albert.cid.presentation.imagedetail

import androidx.lifecycle.*
import com.albert.cid.domain.usecase.GetGroupImagesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageGalleryViewModelImpl(
    private val getGroupImagesUseCase: GetGroupImagesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ImageGalleryViewModel, ViewModel() {

    private val _viewState: MutableLiveData<ImageGalleryState> = MutableLiveData()
    override val viewState: LiveData<ImageGalleryState>
        get() = _viewState

    override fun getImages(id: Int) {
        viewModelScope.launch {
            val results = withContext(ioDispatcher) { getGroupImagesUseCase(id) }
            results.fold(
                onSuccess = {
                    _viewState.value = ImageGalleryState.ShowImages(it)
                },
                onFailure = {
                    _viewState.value = ImageGalleryState.Error

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
        return ImageGalleryViewModelImpl(getGroupImagesUseCase, ioDispatcher) as T
    }
}