package com.android.testcakratech.common.navigator

import android.app.Activity
import android.content.Intent
import com.android.testcakratech.views.form.FormActivity
import com.android.testcakratech.views.main.MainActivity

class ScreenNavigator(
    private val activity: Activity
) {


    fun onBackNavigation() {
        activity.onBackPressed()
    }

    fun toMainActivity() {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }

    fun toFormActivity() {
        activity.startActivity(Intent(activity, FormActivity::class.java))
    }

}