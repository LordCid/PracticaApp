package com.slashmobility.seleccion.albert.cid.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.detail.state.DetailViewState
import com.slashmobility.seleccion.albert.cid.presentation.main.GROUP_ID
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
            is DetailViewState.ShowGroupData -> showData(screenState.group)
            is DetailViewState.NoData -> finish()
        }
    }

    private fun showData(group: Group) {
        supportActionBar?.title = group.name
        title_tv.text = group.name
        date_tv.text = group.dateLong.toString()
        description_short_tv.text = group.descriptionShort
        description_tv.text = group.description
        imagesLoader.loadImage(group.defaultImageUrl, header_container)
        setFavoriteIcon(group.isFavorite)
        favourite_button.setOnClickListener { viewModel.changeFavorite() }
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite){
            favourite_button.setBackgroundResource(R.drawable.ic_favorite_black)
        } else{
            favourite_button.setBackgroundResource(R.drawable.ic_favorite_border_black)
        }
    }


}