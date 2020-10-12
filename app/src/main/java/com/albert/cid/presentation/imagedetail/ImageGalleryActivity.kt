package com.albert.cid.presentation.imagedetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.albert.cid.R
import com.albert.cid.domain.GROUP_ID
import com.albert.cid.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.activity_image_gallery.*

class ImageGalleryActivity : BaseActivity() {

    private lateinit var imagePageAdapter: ImagePageAdapter
    private lateinit var viewModel: ImageGalleryViewModelImpl

    private val groupId: Int by lazy { intent?.extras?.getInt(GROUP_ID) ?: 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_gallery)
        setViewModel()
    }

    private fun setViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[ImageGalleryViewModelImpl::class.java]
        viewModel.viewState.observe(::getLifecycle, ::updateUI)
        viewModel.getImages(groupId)
    }

    private fun updateUI(viewState: ImageGalleryState) {
        when(viewState){
            is ImageGalleryState.ShowImages -> showImages(viewState.images)
            is ImageGalleryState.Error -> finish()
        }
    }

    private fun showImages(images: List<String>) {
        imagePageAdapter = ImagePageAdapter(imagesLoader, supportFragmentManager)
        image_pager.adapter = imagePageAdapter
        imagePageAdapter.urlList = images
    }
}