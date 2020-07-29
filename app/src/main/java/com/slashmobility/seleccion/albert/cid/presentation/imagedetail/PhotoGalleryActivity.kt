package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.activity_photo_gallery.*

class PhotoGalleryActivity : BaseActivity() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var viewModel: PhotoGalleryViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)
        setViewModel()
    }

    private fun setViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[PhotoGalleryViewModelImpl::class.java]
        viewModel.viewState.observe(::getLifecycle, ::updateUI)
        viewModel.getImages()
    }

    private fun updateUI(viewState: PhotoGalleryState) {
        when(viewState){
            is PhotoGalleryState.ShowImages -> showImages(viewState.images)
            is PhotoGalleryState.Error -> finish()
        }
    }

    private fun showImages(images: List<String>) {
        imageAdapter = ImageAdapter(imagesLoader, supportFragmentManager)
        image_pager.adapter = imageAdapter
        imageAdapter.urlList = images
    }
}