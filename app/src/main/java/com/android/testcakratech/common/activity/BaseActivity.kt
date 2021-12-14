package com.android.testcakratech.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.MyApplication
import com.android.testcakratech.root.composition.ActivityCompositionRoot
import com.android.testcakratech.root.composition.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    private val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }
}