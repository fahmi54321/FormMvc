package com.android.testcakratech.view.common.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.android.testcakratech.view.common.navigator.ScreenNavigator

class DialogNavigator(
    private val context: Context
) {

    fun showAlertDialog(
        title: String,
        message: String
    ) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Close") { dialog, which ->
                dialog.dismiss()
            }
        }.show()
    }

    fun dialogBerhasilMenyimpan(
        title: String,
        message: String,
        screenNavigator: ScreenNavigator
    ) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Close") { dialog, which ->
                screenNavigator.toMainActivity()
            }
        }.show()
    }

}