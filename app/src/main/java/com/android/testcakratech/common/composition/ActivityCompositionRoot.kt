package com.android.testcakratech.common.composition

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.view.common.dialog.DialogNavigator
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screenNavigator by lazy {
        ScreenNavigator(activity)
    }
    val dialogNavigator get() = DialogNavigator(activity)

    private val dao get() = appCompositionRoot.dao

    val mainUseCase get() = MainUseCase(dao)
    val formUserCase get() = FormUseCase(dao)

}