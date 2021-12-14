package com.android.testcakratech.common.composition

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.view.common.dialog.DialogNavigator
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import com.android.testcakratech.view.common.viewmvc.ViewMvcFactory
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class ActivityCompositionRoot(
    val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screenNavigator by lazy {
        ScreenNavigator(activity)
    }

    val layoutInflater get() = LayoutInflater.from(activity)
    val dao get() = appCompositionRoot.dao


}