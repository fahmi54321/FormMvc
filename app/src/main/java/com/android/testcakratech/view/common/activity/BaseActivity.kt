package com.android.testcakratech.view.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.android.testcakratech.MyApplication
import com.android.testcakratech.common.composition.ActivityCompositionRoot
import com.android.testcakratech.common.composition.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }
}