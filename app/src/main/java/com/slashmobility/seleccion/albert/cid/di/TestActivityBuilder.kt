package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ImageLoaderModule::class, TestMainListModule::class])
interface TestActivityBuilder {
//    @ContributesAndroidInjector(modules = [TestMainListModule::class])
    @ContributesAndroidInjector
    fun bindTestMainListActivity(): MainActivity
}
