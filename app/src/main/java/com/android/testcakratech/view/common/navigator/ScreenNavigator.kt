package com.android.testcakratech.view.common.navigator

import android.app.Activity
import android.content.Intent
import com.android.testcakratech.view.screens.form.FormActivity
import com.android.testcakratech.view.screens.main.MainActivity

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