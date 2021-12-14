package com.android.testcakratech.view.common.viewmvc

import android.view.LayoutInflater
import com.android.testcakratech.view.screens.form.FormMvcView
import com.android.testcakratech.view.screens.main.MainMvcView

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater
) {
    fun newFormActivity(): FormMvcView {
        return FormMvcView(layoutInflater)
    }

    fun newMainActivity(): MainMvcView {
        return MainMvcView(layoutInflater)
    }
}