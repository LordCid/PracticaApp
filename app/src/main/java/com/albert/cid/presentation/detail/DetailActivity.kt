package com.albert.cid.presentation.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.albert.cid.R
import com.albert.cid.domain.GROUP_ID
import com.albert.cid.domain.model.Group
import com.albert.cid.presentation.common.BaseActivity
import com.albert.cid.presentation.detail.state.DetailViewState
import com.albert.cid.presentation.imagedetail.ImageGalleryActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailActivity : BaseActivity() {

    private lateinit var viewModel: DetailViewModel

    private val groupId: Int by lazy { intent?.extras?.getInt(GROUP_ID) ?: 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setViewModel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[DetailViewModelImpl::class.java]
        viewModel.detailState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroupDetailData(groupId)
    }


    private fun updateUI(screenState: DetailViewState) {
        when(screenState){
            is DetailViewState.ShowGroupData -> {
                showData(screenState.group)
                setClickListeners()
            }
            is DetailViewState.NoData -> finish()
        }
    }

    private fun showData(group: Group) {
        supportActionBar?.title = group.name
        title_tv.text = group.name
        date_tv.text = dateFormat.format(group.dateLong)
        description_short_tv.text = group.descriptionShort
        description_tv.text = group.description
        imagesLoader.loadImage(group.defaultImageUrl, group_container)
        favourite_button.setBackgroundResource(getFavoriteIcon(group.isFavorite))
    }

    private fun setClickListeners(){
        group_container.setOnClickListener { goToPhotoGallery() }
        favourite_button.setOnClickListener { viewModel.changeFavorite() }
    }

    private fun getFavoriteIcon(isFavorite: Boolean): Int{
        return if(isFavorite) R.drawable.ic_favorite_black else R.drawable.ic_favorite_border_black
    }

    private fun goToPhotoGallery(){
        val intent = Intent(this, ImageGalleryActivity::class.java)
        intent.putExtra(GROUP_ID, groupId)
        startActivity(intent)
    }

}