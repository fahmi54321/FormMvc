package com.android.testcakratech.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.view.common.dialog.DialogNavigator
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import com.android.testcakratech.view.common.viewmvc.ViewMvcFactory
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class PresentationCompositionRoot(
    private val activityCompositionRoot: ActivityCompositionRoot
) {

    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val dao get() = activityCompositionRoot.dao

    val dialogNavigator get() = DialogNavigator(activityCompositionRoot.activity)
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val screenNavigator get() = activityCompositionRoot.screenNavigator

    val mainUseCase get() = MainUseCase(dao)
    val formUserCase get() = FormUseCase(dao)
}