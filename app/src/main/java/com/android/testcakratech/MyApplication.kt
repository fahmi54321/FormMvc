package com.android.testcakratech

import android.app.Application
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class MyApplication : Application() {

    private val dao = FormDatabase.getInstance(this.applicationContext)
        .formDao()

    val mainUseCase get() = MainUseCase(dao)
    val formUserCase get() = FormUseCase(dao)

    override fun onCreate() {
        super.onCreate()
    }

}