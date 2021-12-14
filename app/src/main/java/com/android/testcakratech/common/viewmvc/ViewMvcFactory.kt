package com.android.testcakratech.common.viewmvc

import android.view.LayoutInflater
import com.android.testcakratech.views.form.FormMvcView
import com.android.testcakratech.views.main.MainMvcView

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