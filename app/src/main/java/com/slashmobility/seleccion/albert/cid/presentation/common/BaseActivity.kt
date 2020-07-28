package com.slashmobility.seleccion.albert.cid.presentation.common


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.R
import com.xpertai.test.domain.imageloader.ImagesLoader
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var imagesLoader: ImagesLoader
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.NewInstanceFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}