package com.android.testcakratech.root.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.common.navigator.ScreenNavigator

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