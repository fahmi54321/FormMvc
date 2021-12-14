package com.android.testcakratech.view.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.MyApplication

open class BaseActivity:AppCompatActivity() {

    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

}