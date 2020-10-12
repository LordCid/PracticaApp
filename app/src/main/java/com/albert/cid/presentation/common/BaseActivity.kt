package com.albert.cid.presentation.common


import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.albert.cid.domain.common.imageloader.ImagesLoader
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var imagesLoader: ImagesLoader
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.NewInstanceFactory

    protected lateinit var dateFormat: java.text.DateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        dateFormat = DateFormat.getDateFormat(this)
        super.onCreate(savedInstanceState)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}