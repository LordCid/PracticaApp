package com.slashmobility.seleccion.albert.cid.presentation.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import androidx.test.rule.ActivityTestRule
import com.slashmobility.seleccion.albert.cid.di.AppComponentFactory
import com.slashmobility.seleccion.albert.cid.di.TestAppComponent
import com.slashmobility.seleccion.albert.cid.di.TestAppComponentFactory
import com.slashmobility.seleccion.albert.cid.domain.App
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var viewModel: ViewModelProvider.NewInstanceFactory

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App
        val appComponent = TestAppComponentFactory.create(app)
        appComponent.inject(app)

        viewModel = appComponent.getViewModelFactory()

        val intent = Intent(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, MainActivity::class.java
        )

        activityRule.launchActivity(intent)
    }

    @Test
    fun WhenErrorStateShowErrorDialog() {

    }
}