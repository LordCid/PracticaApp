package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.domain.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        TestActivityBuilder::class
    ]
)
interface TestAppComponent: AndroidInjector<DaggerApplication> {

    fun getViewModelFactory():ViewModelProvider.NewInstanceFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): TestAppComponent
    }
}

object TestAppComponentFactory : TestAppComponent.Factory by DaggerTestAppComponent.factory()

