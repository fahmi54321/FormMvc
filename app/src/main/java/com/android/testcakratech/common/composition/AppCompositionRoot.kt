package com.android.testcakratech.common.composition

import android.content.Context
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.screens.form.FormUseCase
import com.android.testcakratech.view.screens.main.MainUseCase

class AppCompositionRoot(
    private val context: Context
) {

    private val dao by lazy {
        FormDatabase.getInstance(context)
            .formDao()
    }

    val mainUseCase get() = MainUseCase(dao)
    val formUserCase get() = FormUseCase(dao)

}