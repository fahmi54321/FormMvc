package com.android.testcakratech

import android.app.Application
import com.android.testcakratech.common.composition.AppCompositionRoot
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {

        appCompositionRoot = AppCompositionRoot(this)
        super.onCreate()
    }

}