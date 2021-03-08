package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface TestActivityBuilder {
    @ContributesAndroidInjector(modules = [MainListModule::class])
    fun bindTestMainListActivity(): MainActivity
}
