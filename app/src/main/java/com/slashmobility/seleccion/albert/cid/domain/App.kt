package com.slashmobility.seleccion.albert.cid.domain


import com.slashmobility.seleccion.albert.cid.di.AppComponent
import com.slashmobility.seleccion.albert.cid.di.AppComponentFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm

open class App : DaggerApplication() {

    open lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = AppComponentFactory.create(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        appComponent.inject(this)
    }
}
