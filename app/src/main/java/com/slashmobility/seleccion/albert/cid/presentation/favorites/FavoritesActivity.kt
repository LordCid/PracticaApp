package com.slashmobility.seleccion.albert.cid.presentation.favorites

import android.os.Bundle
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.presentation.main.BaseActivity

class FavoritesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_favorites)
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}