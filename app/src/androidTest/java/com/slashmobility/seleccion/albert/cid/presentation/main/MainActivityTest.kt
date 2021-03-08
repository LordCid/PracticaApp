package com.slashmobility.seleccion.albert.cid.presentation.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
//import com.nhaarman.mockitokotlin2.given
//import com.nhaarman.mockitokotlin2.mock
import com.slashmobility.seleccion.albert.cid.di.TestAppComponentFactory
import com.slashmobility.seleccion.albert.cid.domain.App
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var getGroupListUseCase: GetGroupListUseCase


    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App
        val appComponent = TestAppComponentFactory.create(app)
        appComponent.inject(app)

        getGroupListUseCase = appComponent.getGroupListUseCase()


        val intent = Intent(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, MainActivity::class.java
        )

        activityRule.launchActivity(intent)
    }


    @Test
    fun whenErrorStateShowErrorDialog() {
        val throwable = mock(Throwable::class.java)
        runBlocking {
            given(getGroupListUseCase.invoke()).willReturn(Result.failure(throwable))

            onView(withText("Error al descargar grupos")).check(matches(isDisplayed()))
        }
    }

}