package com.android.testcakratech.root.composition

import com.android.testcakratech.common.dialog.DialogNavigator
import com.android.testcakratech.common.viewmvc.ViewMvcFactory
import com.android.testcakratech.views.form.FormUseCase
import com.android.testcakratech.views.main.MainUseCase

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