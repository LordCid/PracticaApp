package com.slashmobility.seleccion.albert.cid.domain


import com.slashmobility.seleccion.albert.cid.di.ApplicationComponent
import com.slashmobility.seleccion.albert.cid.di.ApplicationComponentFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm

class App : DaggerApplication() {

    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = ApplicationComponentFactory.create(this)
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        applicationComponent.inject(this)
    }
}
