package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.domain.App
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        TestActivityBuilder::class,
        ImageLoaderModule::class,
        TestDomainModule::class
    ]
)
interface TestAppComponent: AndroidInjector<DaggerApplication> {

    fun getGroupListUseCase(): GetGroupListUseCase

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): TestAppComponent
    }
}

object TestAppComponentFactory : TestAppComponent.Factory by DaggerTestAppComponent.factory()

