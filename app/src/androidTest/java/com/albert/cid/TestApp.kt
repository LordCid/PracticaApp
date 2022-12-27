package com.slashmobility.seleccion.albert.cid
import com.slashmobility.seleccion.albert.cid.di.DaggerAppComponent
import com.slashmobility.seleccion.albert.cid.di.TestAppComponent
import com.slashmobility.seleccion.albert.cid.domain.App
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.internal.MapFactory.builder
import java.util.stream.Stream.builder

class TestApp: App() {

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        appComponent = TestAppComponent
//        return appComponent
//    }
}