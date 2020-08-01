package com.slashmobility.seleccion.albert.cid.presentation.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.GROUP_ID
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.detail.state.DetailViewState
import com.slashmobility.seleccion.albert.cid.presentation.imagedetail.ImageGalleryActivity
import kotlinx.android.synthetic.main.activity_detail.*

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
        imagesLoader.loadImage(group.defaultImageUrl, header_container)
        favourite_button.setBackgroundResource(getFavoriteIcon(group.isFavorite))
    }

    private fun setClickListeners(){
        header_container.setOnClickListener { goToPhotoGallery() }
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