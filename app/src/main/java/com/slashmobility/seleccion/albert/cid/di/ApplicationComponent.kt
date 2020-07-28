package com.slashmobility.seleccion.albert.cid.di


import com.slashmobility.seleccion.albert.cid.domain.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(
    modules = [
        AppModule::class,
        ApplicationProviderModule::class,
        ActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): ApplicationComponent
    }
}

object ApplicationComponentFactory : ApplicationComponent.Factory by DaggerApplicationComponent.factory()