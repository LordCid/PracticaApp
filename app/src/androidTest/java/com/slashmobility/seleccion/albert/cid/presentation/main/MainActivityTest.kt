package com.slashmobility.seleccion.albert.cid.presentation.main

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import androidx.test.rule.ActivityTestRule
import com.slashmobility.seleccion.albert.cid.di.TestAppComponentFactory
import com.slashmobility.seleccion.albert.cid.domain.App
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Mock
    private lateinit var viewModelFactory: MainListViewModelFactory
//    private val viewModel = Mockito.mock(MainListViewModelImpl::class.java)
    private val liveDataViewState = MutableLiveData<MainViewState>()

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App
        val appComponent = TestAppComponentFactory.create(app)
        appComponent.inject(app)

        viewModelFactory = appComponent.getViewModelFactory() as MainListViewModelFactory

        stubViewModelFactory()

        val intent = Intent(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, MainActivity::class.java
        )

        activityRule.launchActivity(intent)
    }


    @Test
    fun whenErrorStateShowErrorDialog() {
        givenErrorState()

        onView(withText("Error al descargar grupos")).check(matches(isDisplayed()))
    }

    private fun stubViewModelFactory() {
//       given(viewModelFactory.create(MainListViewModelImpl::class.java)).willReturn(viewModel)
    }

    private fun givenErrorState() {
        liveDataViewState.value = MainViewState.Error
//        given(viewModel.mainViewState).willReturn(liveDataViewState)
//        given(viewModel.getGroups()).willAnswer { viewModel.mainViewState }
    }
}