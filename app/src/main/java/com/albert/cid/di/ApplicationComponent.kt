package com.albert.cid.di


import com.albert.cid.domain.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Component(
    modules = [
        AppModule::class,
        ApplicationProviderModule::class,
        ActivityBuilder::class,
        DomainModule::class,
        DataModule::class,
        ProvidesModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): AppComponent
    }
}

object AppComponentFactory : AppComponent.Factory by DaggerAppComponent.factory()